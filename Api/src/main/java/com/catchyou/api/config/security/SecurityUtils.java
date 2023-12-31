package com.catchyou.api.config.security;

import com.catchyou.core.exception.SecurityContextNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

public class SecurityUtils {
    private static SimpleGrantedAuthority anonymous = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw SecurityContextNotFoundException.EXCEPTION;
        }

        if (authentication.isAuthenticated() && !authentication.getAuthorities().equals(anonymous)) {
            return Long.valueOf(authentication.getName());
        }
        return 0L;
    }
}
