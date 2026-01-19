/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallResponseMessage extends AbstractJsonEntity {
    @JsonProperty("message")
    private String message;
    @JsonProperty("Message")
    private String message_;
    @JsonProperty("externalBookingID")
    private String externalBookingID;
    @JsonProperty("internalBookingID")
    private String internalBookingID;
    @JsonProperty("errorType")
    private NetquallErrorType errorType;
    @JsonProperty("isRejected")
    private String rejected;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage_() {
        return message_;
    }

    public void setMessage_(String message_) {
        this.message_ = message_;
    }

    public String getExternalBookingID() {
        return externalBookingID;
    }

    public void setExternalBookingID(String externalBookingID) {
        this.externalBookingID = externalBookingID;
    }

    public String getInternalBookingID() {
        return internalBookingID;
    }

    public void setInternalBookingID(String internalBookingID) {
        this.internalBookingID = internalBookingID;
    }

    public NetquallErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(NetquallErrorType errorType) {
        this.errorType = errorType;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }
}
