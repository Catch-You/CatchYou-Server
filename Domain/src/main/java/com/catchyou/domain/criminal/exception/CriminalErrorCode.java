package com.catchyou.domain.criminal.exception;

import com.catchyou.core.annotation.ExplainError;
import com.catchyou.core.dto.ErrorDetail;
import com.catchyou.core.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.catchyou.core.consts.CatchyouStatic.BAD_REQUEST;
import static com.catchyou.core.consts.CatchyouStatic.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum CriminalErrorCode implements BaseErrorCode {
    @ExplainError("지역 선택 시 존재하지 않는 지역일 경우 발생하는 오류입니다.")
    NOT_FOUND_REGION(NOT_FOUND, "Criminal-404-1", "존재하지 않는 지역입니다."),
    @ExplainError("범죄 종류 선택 시 존재하지 않는 종류일 경우 발생하는 오류입니다.")
    NOT_FOUND_CRIMINAL_TYPE(NOT_FOUND, "Criminal-404-2", "존재하지 않는 범죄 종류입니다."),
    @ExplainError("사건 조회 시 존재하지 않을 경우 발생하는 오류입니다.")
    NOT_FOUND_CRIMINAL(NOT_FOUND, "Criminal-404-3", "존재하지 않는 사건입니다."),
    @ExplainError("사건 공개 범위를 공개로 설정 시 몽타주가 확정되지 않아 발생하는 오류입니다.")
    CANNOT_OPEN_TO_PUBLIC(BAD_REQUEST, "Criminal-400-1", "사건을 공개할 수 없습니다."),
    @ExplainError("해당 사건에 권한이 없는 유저가 수정에 접근하려 할 시 발생하는 오류입니다.")
    NOT_VALID_CRIMINAL_USER(BAD_REQUEST, "Criminal-400-2", "해당 유저가 등록한 사건이 아닙니다."),
    @ExplainError("존재하지 않는 사건 코드로 조회 시 발생하는 오류입니다.")
    NOT_FOUND_CRIMINAL_CODE(NOT_FOUND, "Criminal-404-4", "존재하지 않는 사건 코드입니다."),
    @ExplainError("해당 사건에 이미 몽타주 제작자가 존재하는 경우 발생하는 오류입니다.")
    CANNOT_REGISTER_TO_CRIMINAL(BAD_REQUEST, "Criminal-400-3", "이미 몽타주 제작자가 존재하는 사건입니다.");

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
