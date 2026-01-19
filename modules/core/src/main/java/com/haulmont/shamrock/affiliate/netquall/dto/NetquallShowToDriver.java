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

public enum NetquallShowToDriver {
    YES("YES"),
    NO("NO");

    private String code;
    NetquallShowToDriver(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallShowToDriver> map;
    static {
        map = Stream
                .of(NetquallShowToDriver.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static NetquallShowToDriver parseCode(String code) {
        if (StringUtils.isBlank(code))
            return YES;
        NetquallShowToDriver status;
        if ((status = map.get(code)) == null)
            status = YES;
        return status;
    }
}
