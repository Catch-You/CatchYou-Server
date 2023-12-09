package com.catchyou.core.exception;

import com.catchyou.core.annotation.ExplainError;
import com.catchyou.core.dto.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.catchyou.core.consts.CatchyouStatic.*;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    @ExplainError("서버 내부에서 일어난 알 수 없는 오류입니다.")
    INTERNAL_SERVER_ERROR(INTERNAL_SERVER, "Global-500", "서버 내부 오류입니다."),
    @ExplainError("access 토큰 만료 시 발생하는 오류입니다.")
    TOKEN_EXPIRED_ERROR(UNAUTHORIZED, "Auth-401-1","Access Token이 만료되었으므로 재발급받아야 합니다." ),
    @ExplainError("refresh 토큰 만료 시 발생하는 오류입니다.")
    REFRESH_TOKEN_EXPIRED_ERROR(FORBIDDEN, "Auth-403", "Refresh Token이 만료되었으므로 재로그인해야 합니다."),
    @ExplainError("올바르지 않은 access token으로 발생하는 오류입니다.")
    NOT_VALID_ACCESS_TOKEN_ERROR(UNAUTHORIZED, "Auth-401-2", "알맞은 accessToken 을 넣어야 합니다."),
    @ExplainError("인증 토큰이 잘못되어 발생하는 오류입니다.")
    INVALID_TOKEN_ERROR(UNAUTHORIZED, "GLOBAL-401", "잘못된 토큰이므로 다시 로그인 해주세요."),
    @ExplainError("Security context를 찾지 못해 발생하는 오류입니다.")
    SECURITY_CONTEXT_NOT_FOUND_ERROR(INTERNAL_SERVER, "Global-500-2", "Security Context 에러입니다."),
    @ExplainError("인증 메일을 보낼 수 없어 발생하는 오류입니다.")
    MAIL_CAN_NOT_SEND_ERROR(INTERNAL_SERVER, "Mail-500-1", "mail sender 에러입니다."),
    @ExplainError("상태 선택 시 존재하지 않은 상태일 경우 발생하는 오류입니다.")
    NOT_FOUND_STATUS(INTERNAL_SERVER, "Status-404-1", "상태 값은 Y 또는 N이어야 합니다."),
    @ExplainError("몽타주 제작 api 호출 오류입니다.")
    CALL_MONTAGE_API_FAILED(INTERNAL_SERVER, "MontageApi-500-1", "몽타주 제작 api 호출 과정에서 오류가 발생했습니다.");




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
