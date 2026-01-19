/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum NetquallErrorType {
    UNKNOWN(null),
    REJECTED("rejected");

    private String code;
    NetquallErrorType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallErrorType> map;
    static {
        map = Stream
                .of(NetquallErrorType.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static NetquallErrorType parseCode(String code) {
        if (code == null)
            return UNKNOWN;
        NetquallErrorType type;
        if ((type = map.get(code)) == null)
            type = UNKNOWN;
        return type;
    }
}
