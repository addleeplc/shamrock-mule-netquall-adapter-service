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

public enum NetquallStatus {
    UNKNOWN(null),
    SUCCESS("Success"),
    ERROR("Error");

    private String code;
    NetquallStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallStatus> map;
    static {
        map = Stream
                .of(NetquallStatus.values())
                .filter(s -> s.getCode() != null)
                .collect(Collectors.toMap(s -> s.code.toLowerCase(), Function.identity()));
    }

    @JsonCreator
    public static NetquallStatus parseCode(String code) {
        if (StringUtils.isBlank(code))
            return UNKNOWN;
        NetquallStatus status;
        if ((status = map.get(code.toLowerCase())) == null)
            status = UNKNOWN;
        return status;
    }

}
