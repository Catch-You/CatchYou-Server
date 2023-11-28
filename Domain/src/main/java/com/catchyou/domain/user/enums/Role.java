package com.catchyou.domain.user.enums;

import com.catchyou.core.exception.BaseException;
import com.catchyou.core.exception.GlobalErrorCode;
import com.catchyou.domain.user.exception.AuthErrorCode;
import com.catchyou.domain.user.exception.UserErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    DIRECTER("ROLE_DIRECTOR"),
    GUEST("ROLE_GUEST"),
    POLICE("ROLE_POLICE");

    private static final Map<String, Role> roleMap = Stream.of(values())
            .collect(Collectors.toMap(Role::getAuthority, Function.identity()));
    @JsonValue
    private final String authority;

    @JsonCreator
            public static Role resolve(String authority){
        return Optional.ofNullable(roleMap.get(authority))
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_ROLE));
    }

    Role(String authority) {
        this.authority = authority;
    }
    public String getAuthority() {
        return authority;
    }
}
