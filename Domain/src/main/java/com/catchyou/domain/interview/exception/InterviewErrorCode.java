package com.catchyou.domain.interview.exception;

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
public enum InterviewErrorCode implements BaseErrorCode {
    @ExplainError("인터뷰 조회 시 존재하지 않는 인터뷰일 경우 발생하는 오류입니다.")
    NOT_FOUND_INTERVIEW(NOT_FOUND, "Interview-404-1", "존재하지 않는 인터뷰입니다."),
    @ExplainError("사건에 대해 선택된 인터뷰가 없는 경우 발생하는 오류입니다.")
    NOT_FOUND_SELECTED_INTERVIEW(NOT_FOUND, "Interview-404-2", "사건에 대해 선택된 인터뷰가 없습니다."),

    @ExplainError("해당 사건에 권한이 없는 몽타주 제작자가 접근하려 할 시 발생하는 오류입니다.")
    NOT_VALID_INTERVIEW_DIRECTOR(BAD_REQUEST, "Interview-400-1", "해당 유저가 몽타주를 제작할 수 있는 사건이 아닙니다."),
    @ExplainError("이미 인터뷰에 대해 선택된 몽타주가 있는 경우 발생하는 오류입니다.")
    CANNOT_CREATE_MONTAGE_IN_INTERVIEW(BAD_REQUEST, "Interview-400-2", "이 인터뷰에서 이미 몽타주를 선택했습니다.");

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
