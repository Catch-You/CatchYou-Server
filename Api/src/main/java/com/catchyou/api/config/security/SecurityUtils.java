package com.catchyou.api.config.security;

import com.catchyou.core.exception.SecurityContextNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw SecurityContextNotFoundException.EXCEPTION;
        }

        if (authentication.isAuthenticated()) {
            return Long.valueOf(authentication.getName());
        }
        return 0L;
    }
}
