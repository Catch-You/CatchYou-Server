package com.catchyou.domain.criminal.enums;

import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.criminal.exception.CriminalErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CrimeType {
    강도("강도"),
    살인("살인(미수)"),
    절도("절도"),
    성범죄("성범죄"),
    방화("방화"),
    사기("사기"),
    마약("마약"),
    도박("도박"),
    특경법("특경법");

    private static final Map<String, CrimeType> crimeTypeMap = Stream.of(values())
            .collect(Collectors.toMap(CrimeType::getType, Function.identity()));
    @JsonValue
    private final String type;

    @JsonCreator
    public static CrimeType resolve(String type){
        return Optional.ofNullable(crimeTypeMap.get(type))
                .orElseThrow(() -> new BaseException(CriminalErrorCode.NOT_FOUND_REGION));
    }

    CrimeType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
