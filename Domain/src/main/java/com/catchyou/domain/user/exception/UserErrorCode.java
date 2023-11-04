package com.catchyou.domain.user.exception;

import com.catchyou.core.annotation.ExplainError;
import com.catchyou.core.dto.ErrorDetail;
import com.catchyou.core.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    @ExplainError("회원 정보 조회 시 존재하지 않는 회원일 경우 발생하는 오류입니다.")
    NOT_FOUND_USER(404, "User-404", "존재하지 않는 사용자입니다."),

    @ExplainError("이미 존재하는 이메일을 사용하려고 할 때 발생하는 오류입니다.")
    ALREADY_EXIST_EMAIL(400, "User-400", "이미 존재하는 이메일입니다.");

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
