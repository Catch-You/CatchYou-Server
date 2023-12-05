package com.catchyou.core.jwt;

import com.catchyou.core.dto.AccessTokenInfo;
import com.catchyou.core.exception.ExpiredRefreshTokenException;
import com.catchyou.core.exception.ExpiredTokenException;
import com.catchyou.core.exception.InvalidTokenException;
import com.catchyou.core.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static com.catchyou.core.consts.CatchyouStatic.*;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class JwtProvider {

    private final JwtProperties jwtProperties;

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)
        );
    }

    private String buildAccessToken(
            Long id, Date issuedAt, Date accessExpiresIn, String role
    ) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .claim(TOKEN_ROLE, role)
                .setExpiration(accessExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    private String buildRefreshToken(
            Long id, Date issuedAt, Date refreshExpiresIn
    ){
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .setExpiration(refreshExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    public String generateAccessToken(Long id, String role) {
        final Date issuedAt = new Date();
        final Date accessExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getAccessExp()* MILLI_TO_SECOND);

        return buildAccessToken(id, issuedAt, accessExpiresIn, role);
    }

    public String generateRefreshToken(Long id) {
        final Date issuedAt = new Date();
        final Date refreshExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * MILLI_TO_SECOND);
        return buildRefreshToken(id, issuedAt, refreshExpiresIn);
    }

    public boolean isAccessToken(String token) {
        String typeClaim = getJws(token).getBody().get(TOKEN_TYPE).toString();
        log.info("Type claim value: {}", typeClaim);
        return typeClaim.equals(ACCESS_TOKEN);
    }

    public boolean isRefreshToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(REFRESH_TOKEN);
    }

    public AccessTokenInfo parseAccessToken(String token) {
        if(isAccessToken(token)) {
            Claims claims = getJws(token).getBody();
            return AccessTokenInfo.builder()
                    .userId(Long.parseLong(claims.getSubject()))
                    .role((String) claims.get(TOKEN_ROLE))
                    .build();
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public Long parseRefreshToken(String token) {
        try {
            if(isRefreshToken(token)) {
                Claims claims = getJws(token).getBody();
                return Long.parseLong(claims.getSubject());
            }
        } catch (ExpiredTokenException e) {
            throw ExpiredRefreshTokenException.EXCEPTION;
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public Long getRefreshTokenTTlSecond() {
        return jwtProperties.getRefreshExp();
    }

    public Long getAccessTokenTTlSecond() {
        return jwtProperties.getAccessExp();
    }

    public Long getLeftAccessTokenTTlSecond(String token){
        return getJws(token).getBody().getExpiration().getTime();
    }
}
