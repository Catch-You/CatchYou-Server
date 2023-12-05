package com.catchyou.domain.criminal.enums;

import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.criminal.exception.CriminalErrorCode;
import com.catchyou.domain.user.enums.Role;
import com.catchyou.domain.user.exception.UserErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Region {
    서울("서울"),
    인천("인천"),
    경기("경기"),
    강원("강원"),
    충청("충청"),
    전라("전라"),
    대구("대구"),
    부산("부산"),
    경상("경상"),
    제주("제주");

    private static final Map<String, Region> regionMap = Stream.of(values())
            .collect(Collectors.toMap(Region::getName, Function.identity()));
    @JsonValue
    private final String name;

    @JsonCreator
    public static Region resolve(String name){
        return Optional.ofNullable(regionMap.get(name))
                .orElseThrow(() -> new BaseException(CriminalErrorCode.NOT_FOUND_REGION));
    }

    Region(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
