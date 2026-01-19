/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallDetailedRate extends AbstractJsonEntity {
    @JsonProperty("RateName")
    private String rateName;
    @JsonProperty("RateLabelShortCode")
    private String rateLabelShortCode;
    @JsonProperty("RateType")
    private String rateType;
    @JsonProperty("BaseRate")
    private BigDecimal baseRate;
    @JsonProperty("Multiple")
    private BigDecimal multiple;
    @JsonProperty("Percentage")
    private BigDecimal percentage;
    @JsonProperty("RateSequence")
    private BigDecimal rateSequence;
    @JsonProperty("ShortCode")
    private String shortCode;

    @JsonProperty("Amount")
    private BigDecimal Amount;

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public String getRateLabelShortCode() {
        return rateLabelShortCode;
    }

    public void setRateLabelShortCode(String rateLabelShortCode) {
        this.rateLabelShortCode = rateLabelShortCode;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public BigDecimal getRateSequence() {
        return rateSequence;
    }

    public void setRateSequence(BigDecimal rateSequence) {
        this.rateSequence = rateSequence;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }
}
