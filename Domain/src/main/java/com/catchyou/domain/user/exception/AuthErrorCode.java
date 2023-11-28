package com.catchyou.domain.user.exception;

import com.catchyou.core.annotation.ExplainError;
import com.catchyou.core.dto.ErrorDetail;
import com.catchyou.core.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.catchyou.core.consts.CatchyouStatic.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    @ExplainError("인증에 사용되는 액세스 토큰이 만료되었을 때 발생하는 오류입니다.")
    EXPIRED_TOKEN_ERROR(UNAUTHORIZED, "401-1", "토큰이 만료되었습니다."),
    @ExplainError("인증에 사용되는 토큰의 정보가 올바르지 않을 때 발생하는 오류입니다.")
    INVALID_TOKEN_ERROR(UNAUTHORIZED, "401-2", "올바르지 않은 토큰입니다."),
    @ExplainError("액세스 토큰을 재발급 받기 위한 리프레시 토큰이 만료되었을 때 발생하는 오류입니다.")
    EXPIRED_REFRESH_TOKEN_ERROR(UNAUTHORIZED, "401-3", "리프레시 토큰이 만료되었습니다."),
    @ExplainError("인증에 사용되는 토큰이 헤더에 존재하지 않을 때 발생하는 오류입니다.")
    NOT_VALID_ACCESS_TOKEN_ERROR(UNAUTHORIZED, "401-4", "알맞은 accessToken 을 넣어주세요."),
    @ExplainError("비밀번호가 올바르지 않아 발생하는 오류입니다.")
    NOT_MATCH_PASSWORD_ERROR(UNAUTHORIZED, "401-5", "알맞은 비밀번호를 입력해야 합니다.");

    private final Integer statusCode;
    private final String errorCode;
    private final String reason;

    @Override
    public ErrorDetail getErrorDetail() {
        return ErrorDetail.of(statusCode, errorCode, reason);
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
