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

public enum NetquallHeaderMethod {
    UNKNOWN(null),
    GET_QUOTE("GetQuote"),
    RESERVE("Reserve"),
    STATUS_UPDATES("StatusUpdates"),
    DRIVER_LOCATION("DriverLocation"),
    PAYMENT_STATUS("PaymentStatus"),
    FINAL_CLOSEOUT("FinalCloseout"),
    REQUEST_OVERRIDE("RequestOverride"), // TODO: what is it?
    CANCEL_RESERVATION("CancelReservation"),
    PROVIDER_CANCEL("ProviderCancel"),
    PROVIDER_RESERVATION_UPDATE("ProviderReservationUpdate"),
    AUDIT_RESPONSE("AuditResponse"),
    PAYMENT_STATUS_CALLBACK("PaymentStatusCallback"),
    STATUS_UPDATES_CALLBACK("StatusUpdatesCallback");

    private String code;
    NetquallHeaderMethod(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, NetquallHeaderMethod> map;
    static {
        map = Stream
                .of(NetquallHeaderMethod.values())
                .collect(Collectors.toMap(s -> s.code, Function.identity()));
    }

    @JsonCreator
    public static NetquallHeaderMethod parseCode(String code) {
        if (StringUtils.isBlank(code))
            return UNKNOWN;
        NetquallHeaderMethod status;
        if ((status = map.get(code)) == null)
            status = UNKNOWN;
        return status;
    }
}
