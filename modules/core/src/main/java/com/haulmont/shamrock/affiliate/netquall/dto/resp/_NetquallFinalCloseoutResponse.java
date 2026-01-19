/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.resp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeader;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallResponseMessage;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class _NetquallFinalCloseoutResponse extends NetquallRefResponse {
    @JsonProperty("Header")
    private NetquallHeader header;
    @JsonProperty("ResponseMessage")
    private NetquallResponseMessage responseMessage;
    @JsonProperty("TransactionReference")
    private String transactionReference;

    public NetquallHeader getHeader() {
        return header;
    }

    public void setHeader(NetquallHeader header) {
        this.header = header;
    }

    public NetquallResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(NetquallResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }
}
