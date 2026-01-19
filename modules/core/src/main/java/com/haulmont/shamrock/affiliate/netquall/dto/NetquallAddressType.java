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

public enum NetquallAddressType {
    UNKNOWN(null),
    ADDRESS("Address"),
    AIRPORT("Airport"),
    RAILWAYSTATION("RailwayStation"),
    FBO("FBO"),
    SEAPORT("Seaport");

    private String code;
    NetquallAddressType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallAddressType> map;
    static {
        map = Stream
                .of(NetquallAddressType.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static NetquallAddressType parseCode(String code) {
        if (StringUtils.isBlank(code))
            return UNKNOWN;
        NetquallAddressType status;
        if ((status = map.get(code)) == null)
            status = UNKNOWN;
        return status;
    }
}
