/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.model;

import java.util.HashMap;
import java.util.Map;

public enum ExecutionStatus {
    UNKNOWN(-1),
    BOOKED(0),
    WAITING_ALLOC(10),
    ALLOCATED(20),
    CONFIRMED(21),
    ON_WAY(30),
    AT_PICKUP(40),
    ON_BOARD(50),
    DONE(60),
    CANCELLED(70),
    ON_HOLD(-70);

    private static Map<Integer, ExecutionStatus> executionStatusByCode = new HashMap<>();

    static {
        for (ExecutionStatus executionStatus : ExecutionStatus.values()) {
            executionStatusByCode.put(executionStatus.getCode(), executionStatus);
        }
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

    ExecutionStatus(Integer code) {
        this.code = code;
    }

    public static ExecutionStatus valueOf(Integer code) {
        if (code == null) {
            return null;
        }
        ExecutionStatus executionStatus = executionStatusByCode.get(code);
        if (executionStatus == null) {
            executionStatus = UNKNOWN;
        }
        return executionStatus;
    }
}
