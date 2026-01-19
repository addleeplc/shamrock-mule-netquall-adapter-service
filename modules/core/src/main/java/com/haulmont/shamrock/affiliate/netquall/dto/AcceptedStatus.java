/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.haulmont.bali.lang.StringUtils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AcceptedStatus {
    UNKNOWN(null),
    ACCEPTED("1"),
    PENDING("0"),
    REJECTED("-1");

    private String code;
    AcceptedStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, AcceptedStatus> map;
    static {
        map = Stream
                .of(AcceptedStatus.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static AcceptedStatus parseCode(String code) {
        if (code == null)
            return UNKNOWN;
        AcceptedStatus status;
        if ((status = map.get(code)) == null)
            status = UNKNOWN;
        return status;
    }
}
