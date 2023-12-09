package com.catchyou.domain.montage.exception;

import com.catchyou.core.annotation.ExplainError;
import com.catchyou.core.dto.ErrorDetail;
import com.catchyou.core.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.catchyou.core.consts.CatchyouStatic.INTERNAL_SERVER;
import static com.catchyou.core.consts.CatchyouStatic.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum MontageErrorCode implements BaseErrorCode {
    @ExplainError("몽타주 조회 시 존재하지 않는 몽타주일 경우 발생하는 오류입니다.")
    NOT_FOUND_MONTAGE(NOT_FOUND, "Montage-404-1", "존재하지 않는 몽타주입니다."),
    @ExplainError("인터뷰에 대해 선택된 몽타주가 없는 경우 발생하는 오류입니다.")
    NOT_FOUND_SELECTED_MONTAGE(NOT_FOUND, "Montage-404-2", "인터뷰에 대해 선택된 몽타주가 없습니다.");


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
