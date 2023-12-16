package com.catchyou.api.auth.service;

import com.catchyou.api.auth.controller.AuthRequest;
import com.catchyou.api.auth.controller.AuthResponse;
import com.catchyou.api.config.security.JwtTokenFilter;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.core.exception.BaseException;
import com.catchyou.core.jwt.JwtProvider;
import com.catchyou.domain.user.repository.UserRepository;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.catchyou.core.consts.CatchyouStatic.*;
import static com.catchyou.domain.user.exception.AuthErrorCode.NOT_MATCH_PASSWORD_ERROR;
import static com.catchyou.domain.user.exception.UserErrorCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final UserAdaptor userAdaptor;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;
    private final JwtTokenFilter jwtTokenFilter;

    @Transactional
    public AuthResponse login(AuthRequest request){
        User user = checkEmail(request.getEmail());
        verifyPassword(request.getPassword(), user.getPassword());

        AuthResponse authResponse = createAuthResponse(user);

        return authResponse;
    }

    @Transactional
    public AuthResponse reissue(String requestRefreshToken){
        Long userId = jwtProvider.parseRefreshToken(requestRefreshToken);

        User user = userAdaptor.findById(userId);
        return createAuthResponse(user);
    }

    private User checkEmail(String email){
       return userRepository.findByEmail(email)
               .orElseThrow(() -> new BaseException(NOT_FOUND_USER));
    }


    private void verifyPassword(String request, String password){
        if(!passwordEncoder.matches(request, password))
            throw new BaseException(NOT_MATCH_PASSWORD_ERROR);
    }

    private String resolveAccessToken(Long userId, String role){
        return jwtProvider.generateAccessToken(userId, role);
    }

    private String resolveRefreshToken(Long userId){
        String refreshToken= jwtProvider.generateRefreshToken(userId);
        redisTemplate.opsForValue().set(REFRESH_TOKEN + userId, refreshToken);
        redisTemplate.expire(REFRESH_TOKEN + userId, jwtProvider.getRefreshTokenTTlSecond(),
                TimeUnit.SECONDS);
        return refreshToken;
    }

    private AuthResponse createAuthResponse(User user){
        return AuthResponse.of(
                resolveAccessToken(user.getId(), user.getRole().getAuthority()),
                resolveRefreshToken(user.getId()),
                user,
                jwtProvider.getAccessTokenTTlSecond()
        );
    }
}
