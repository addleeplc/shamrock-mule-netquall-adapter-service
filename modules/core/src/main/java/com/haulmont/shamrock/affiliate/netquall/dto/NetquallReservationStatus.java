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

public enum NetquallReservationStatus {
    UNKNOWN(null),
    CONFIRMED("CON"),
    REJECTED("REJ"),
    ASSIGNED("ASN"),
    EN_ROUTE("ENW"),
    ON_LOCATION("OL"),
    CIRCLING("CIRC"),
    CUSTOMER_IN_CAR("CIC"),
    DROP_OFF("DO"),
    DONE("DON"),
    NO_SHOW("NSH");

    private String code;
    NetquallReservationStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallReservationStatus> map;
    static {
        map = Stream
                .of(NetquallReservationStatus.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static NetquallReservationStatus parseCode(String code) {
        if (StringUtils.isBlank(code))
            return UNKNOWN;
        NetquallReservationStatus status;
        if ((status = map.get(code)) == null)
            status = UNKNOWN;
        return status;
    }
}
