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

public enum NetquallVehicleCode {
    UNKNOWN(null),
    SSED("SSED"),
    ESED("ESED"),
    LSED("LSED"),
    SUV5("SUV5"),
    SUV6("SUV6"),
    VAN("VAN"),
    SUV7("SUV7"),
    LSUV7("LSUV7"),
    ASED ("ASED"),
    ASUV5("ASUV5"),
    BUS("BUS"),
    MOTORCOACH("MOTORCOACH"),
    MINICOACH("MINICOACH"),
    SUVSTRETCH("SUVSTRETCH"),
    STRETCH("STRETCH");

    private String code;
    NetquallVehicleCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallVehicleCode> map;
    static {
        map = Stream
                .of(NetquallVehicleCode.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static NetquallVehicleCode parseCode(String code) {
        if (StringUtils.isBlank(code))
            return UNKNOWN;
        NetquallVehicleCode status;
        if ((status = map.get(code)) == null)
            status = UNKNOWN;
        return status;
    }
}
