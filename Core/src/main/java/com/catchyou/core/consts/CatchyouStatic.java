package com.catchyou.core.consts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CatchyouStatic {

    public static final String TOKEN_ISSUER = "CatchYou";
    public static final String TOKEN_TYPE = "type";
    public static final String TOKEN_ROLE = "role";
    public static final String AUTH_HEADER="Authorization";
    public static final String BEARER="Bearer ";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    public static final String SAMESITE_NONE = "None";

    public static final int COOKIE_EXPIRATION = 7776000;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int UNAUTHORIZED = 401;

    public static final int FORBIDDEN = 403;
    public static final int INTERNAL_SERVER = 500;

    public static final int MILLI_TO_SECOND=1000;

    public static final Set<String> IGNORED_LOGGING_URI_SET = new HashSet<>(Arrays.asList(
            "/",
            "/csrf"
    ));

    public static final String PROVIDER= "Server";
    public static final String API_PREFIX = "/api";

}
