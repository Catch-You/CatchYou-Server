package com.catchyou.domain.user.enums;

public enum Role {
    DIRECTER("ROLE_DIRECTOR"),
    GUEST("ROLE_GUEST"),
    POLICE("ROLE_POLICE");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }
    public String getAuthority() {
        return authority;
    }
}
