package com.catchyou.api.config.security;

import com.catchyou.core.dto.AccessTokenInfo;
import com.catchyou.core.dto.ErrorResponse;
import com.catchyou.core.exception.BaseErrorCode;
import com.catchyou.core.exception.BaseException;
import com.catchyou.core.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

import static com.catchyou.core.consts.CatchyouStatic.*;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        String token = resolveToken(request);

        if(token != null && jwtProvider.isAccessToken(token)){
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        //쿠키로 만듦
        Cookie cookie = WebUtils.getCookie(request, REFRESH_TOKEN);
        if(cookie != null)
            return cookie.getValue();

        String header = request.getHeader(AUTH_HEADER);

        //Bearer 토큰인지 검증한 후 return

        if(header != null

                && header.length() > BEARER.length()
                && header.startsWith(BEARER)
        )
            return header.substring(BEARER.length());

        return null;
    }

    public Authentication getAuthentication(String token){
        AccessTokenInfo accessTokenInfo = jwtProvider.parseAccessToken(token);

        UserDetails userDetails = new AuthDetails(
                accessTokenInfo.getUserId().toString(), accessTokenInfo.getRole()
        );

        return new UsernamePasswordAuthenticationToken(
                userDetails, "user", userDetails.getAuthorities()
        );
    }

}
