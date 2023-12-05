package com.catchyou.api.config.security;

import com.catchyou.core.dto.ErrorDetail;
import com.catchyou.core.dto.ErrorResponse;
import com.catchyou.core.exception.BaseErrorCode;
import com.catchyou.core.exception.BaseException;
import com.catchyou.core.exception.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class AccessDeniedFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    private AuthenticationTrustResolver authenticationTrustResolver =
            new AuthenticationTrustResolverImpl();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BaseException e) {
            exceptionHandle(response, getErrorResponse(e.getErrorCode(), request.getRequestURL().toString()));
        } catch (AccessDeniedException e) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                log.error("Access denied for user: {} - Request URL: {} - Role : {}", authentication.getName(), request.getRequestURL(), authentication.getAuthorities());
            } else {
                log.error("Access denied for anonymous user - Request URL: {}", request.getRequestURL());
            }
            ErrorResponse access_denied =
                    new ErrorResponse(
                            GlobalErrorCode.NOT_VALID_ACCESS_TOKEN_ERROR.getErrorDetail(),
                            request.getRequestURL().toString());
            exceptionHandle(response, access_denied);
        }
    }

    private ErrorResponse getErrorResponse(BaseErrorCode errorCode, String path) {
        ErrorDetail errorDetail = errorCode.getErrorDetail();
        return new ErrorResponse(errorDetail, path);
    }

    private void exceptionHandle(HttpServletResponse response, ErrorResponse errorResponse)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatusCode());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
