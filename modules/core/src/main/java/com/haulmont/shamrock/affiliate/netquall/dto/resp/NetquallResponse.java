/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.resp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallStatus;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallResponse extends AbstractJsonEntity {
    @JsonProperty("Status")
    private NetquallStatus status;
    @JsonProperty("Message")
    private String message;

    public NetquallResponse() {
        setStatus(NetquallStatus.SUCCESS);
    }

    public NetquallResponse(String message) {
        this(NetquallStatus.ERROR, message);
    }

    public NetquallResponse(NetquallStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public NetquallStatus getStatus() {
        return status;
    }

    public void setStatus(NetquallStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
