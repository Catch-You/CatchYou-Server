package com.catchyou.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    Y("true"),
    N("false");

    final String value;
}
