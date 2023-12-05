package com.catchyou.domain.common;

import com.catchyou.core.exception.BaseException;
import com.catchyou.core.exception.GlobalErrorCode;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.exception.CriminalErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Status {
    Y("Y"),
    N("N");

    @JsonValue
    private final String value;

    private static final Map<String, Status> statusMap = Stream.of(values())
            .collect(Collectors.toMap(Status::getValue, Function.identity()));

    @JsonCreator
    public static Status resolve(String value){
        return Optional.ofNullable(statusMap.get(value))
                .orElseThrow(() -> new BaseException(GlobalErrorCode.NOT_FOUND_STATUS));
    }

    Status(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
