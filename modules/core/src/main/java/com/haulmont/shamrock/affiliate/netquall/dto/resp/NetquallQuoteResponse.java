/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.resp;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallRateData;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallQuoteResponse extends NetquallResponse {
    @JsonProperty("QuoteReference")
    private String quoteReference;

    @JsonProperty("RateData")
    private List<NetquallRateData> rateData;


    public String getQuoteReference() {
        return quoteReference;
    }

    public void setQuoteReference(String quoteReference) {
        this.quoteReference = quoteReference;
    }

    public List<NetquallRateData> getRateData() {
        return rateData;
    }

    public void setRateData(List<NetquallRateData> rateData) {
        this.rateData = rateData;
    }
}

