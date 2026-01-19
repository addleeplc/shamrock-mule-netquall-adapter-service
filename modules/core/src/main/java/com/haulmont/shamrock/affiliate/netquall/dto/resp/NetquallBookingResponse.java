/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.resp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.netquall.dto.AcceptedStatus;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallBookingResponse extends NetquallRefResponse {
    @JsonProperty("Accepted")
    private AcceptedStatus accepted;
    @JsonProperty("Version")
    private String version;

    public AcceptedStatus getAccepted() {
        return accepted;
    }

    public void setAccepted(AcceptedStatus accepted) {
        this.accepted = accepted;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
