package com.catchyou.api.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.catchyou.core.consts.CatchyouStatic.API_PREFIX;
import static com.catchyou.core.consts.CatchyouStatic.IGNORED_LOGGING_URI_SET;
import static com.catchyou.domain.user.enums.Role.*;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final FilterConfig filterConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests.requestMatchers(
                                new AntPathRequestMatcher(API_PREFIX + "/auth/login"),
                                        new AntPathRequestMatcher(API_PREFIX + "/signup/**"),
                                new AntPathRequestMatcher(API_PREFIX + "/auth/login/reissue"),
                                new AntPathRequestMatcher(API_PREFIX + "/criminal/open/**"),
                        new AntPathRequestMatcher(API_PREFIX + "/favicon.ico")
                        ).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher(API_PREFIX + "/criminal/police/**")
                        ).hasRole(String.valueOf(POLICE))
                        .requestMatchers(
                                new AntPathRequestMatcher(API_PREFIX + "/criminal/director/**"),
                                new AntPathRequestMatcher(API_PREFIX + "/interview/**"),
                                new AntPathRequestMatcher(API_PREFIX + "/montage/**")
                        ).hasRole(String.valueOf(DIRECTOR))
                        .anyRequest().authenticated());

        http.apply(filterConfig);

        return http.build();
    }

}
