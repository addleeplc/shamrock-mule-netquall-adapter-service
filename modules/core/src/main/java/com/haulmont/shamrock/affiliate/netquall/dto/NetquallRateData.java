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
public class NetquallRateData extends AbstractJsonEntity {
    @JsonProperty("VehicleCode")
    private NetquallVehicleCode vehicleCode;
    @JsonProperty("ServiceType")
    private NetquallServiceType serviceType;

    @JsonProperty("CurrencyCode")
    private String currencyCode;

    @JsonProperty("GrandTotal")
    private BigDecimal grandTotal;
    @JsonProperty("BookingPolicies")
    private String bookingPolicies;
    @JsonProperty("CancellationPolicies")
    private String cancellationPolicies;
    @JsonProperty("FareBreakdown")
    private NetquallRate fareBreakdown;

    public NetquallVehicleCode getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(NetquallVehicleCode vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public NetquallServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(NetquallServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getBookingPolicies() {
        return bookingPolicies;
    }

    public void setBookingPolicies(String bookingPolicies) {
        this.bookingPolicies = bookingPolicies;
    }

    public String getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(String cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public NetquallRate getFareBreakdown() {
        return fareBreakdown;
    }

    public void setFareBreakdown(NetquallRate fareBreakdown) {
        this.fareBreakdown = fareBreakdown;
    }
}
