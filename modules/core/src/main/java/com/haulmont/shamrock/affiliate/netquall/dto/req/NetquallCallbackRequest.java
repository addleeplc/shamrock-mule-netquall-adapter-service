/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.req;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallResponseMessage;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallCallbackRequest extends NetquallBaseRequest {
    @JsonProperty("ResponseData")
    private NetquallResponseMessage responseData;

    public NetquallResponseMessage getResponseData() {
        return responseData;
    }

    public void setResponseData(NetquallResponseMessage responseData) {
        this.responseData = responseData;
    }
}
