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
public class NetquallRate extends AbstractJsonEntity {
    @JsonProperty("BaseFare")
    private BigDecimal baseFare;
    @JsonProperty("StopCharge")
    private BigDecimal stopCharge;
    @JsonProperty("WaitTime")
    private BigDecimal waitTime;
    @JsonProperty("Tolls")
    private BigDecimal tolls;
    @JsonProperty("Parking")
    private BigDecimal parking;
    @JsonProperty("MeetGreet")
    private BigDecimal meetGreet;
    @JsonProperty("WorkersComp")
    private BigDecimal workersComp;
    @JsonProperty("PackageCharge")
    private BigDecimal packageCharge;
    @JsonProperty("Tax")
    private BigDecimal tax;
    @JsonProperty("StateTax")
    private BigDecimal stateTax;
    @JsonProperty("ServiceCharge")
    private BigDecimal serviceCharge;
    @JsonProperty("EventFee")
    private BigDecimal eventFee;
    @JsonProperty("PerHour")
    private BigDecimal perHour;
    @JsonProperty("PerMinute")
    private BigDecimal perMinute;
    @JsonProperty("PerMile")
    private BigDecimal perMile;
    @JsonProperty("PerKM")
    private BigDecimal perKM;
    @JsonProperty("AirportFee")
    private BigDecimal airportFee;
    @JsonProperty("StopCharge1")
    private BigDecimal stopCharge1;
    @JsonProperty("StopCharge2")
    private BigDecimal stopCharge2;
    @JsonProperty("NoShowCharge")
    private BigDecimal noShowCharge;
    @JsonProperty("RoundTripCharge")
    private BigDecimal roundTripCharge;
    @JsonProperty("VoucherFee")
    private BigDecimal voucherFee;
    @JsonProperty("MinimumFare")
    private BigDecimal minimumFare;
    @JsonProperty("CongestionSurcharge")
    private BigDecimal congestionSurcharge;
    @JsonProperty("BookingFee")
    private BigDecimal bookingFee;
    @JsonProperty("Discount")
    private BigDecimal discount;
    @JsonProperty("FuelSurcharge")
    private BigDecimal fuelSurcharge;
    @JsonProperty("STCCharges")
    private BigDecimal sTCCharges;
    @JsonProperty("SuggestedGratuity")
    private BigDecimal suggestedGratuity;

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public BigDecimal getStopCharge() {
        return stopCharge;
    }

    public void setStopCharge(BigDecimal stopCharge) {
        this.stopCharge = stopCharge;
    }

    public BigDecimal getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(BigDecimal waitTime) {
        this.waitTime = waitTime;
    }

    public BigDecimal getTolls() {
        return tolls;
    }

    public void setTolls(BigDecimal tolls) {
        this.tolls = tolls;
    }

    public BigDecimal getParking() {
        return parking;
    }

    public void setParking(BigDecimal parking) {
        this.parking = parking;
    }

    public BigDecimal getMeetGreet() {
        return meetGreet;
    }

    public void setMeetGreet(BigDecimal meetGreet) {
        this.meetGreet = meetGreet;
    }

    public BigDecimal getWorkersComp() {
        return workersComp;
    }

    public void setWorkersComp(BigDecimal workersComp) {
        this.workersComp = workersComp;
    }

    public BigDecimal getPackageCharge() {
        return packageCharge;
    }

    public void setPackageCharge(BigDecimal packageCharge) {
        this.packageCharge = packageCharge;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getEventFee() {
        return eventFee;
    }

    public void setEventFee(BigDecimal eventFee) {
        this.eventFee = eventFee;
    }

    public BigDecimal getPerHour() {
        return perHour;
    }

    public void setPerHour(BigDecimal perHour) {
        this.perHour = perHour;
    }

    public BigDecimal getPerMinute() {
        return perMinute;
    }

    public void setPerMinute(BigDecimal perMinute) {
        this.perMinute = perMinute;
    }

    public BigDecimal getPerMile() {
        return perMile;
    }

    public void setPerMile(BigDecimal perMile) {
        this.perMile = perMile;
    }

    public BigDecimal getPerKM() {
        return perKM;
    }

    public void setPerKM(BigDecimal perKM) {
        this.perKM = perKM;
    }

    public BigDecimal getAirportFee() {
        return airportFee;
    }

    public void setAirportFee(BigDecimal airportFee) {
        this.airportFee = airportFee;
    }

    public BigDecimal getStopCharge1() {
        return stopCharge1;
    }

    public void setStopCharge1(BigDecimal stopCharge1) {
        this.stopCharge1 = stopCharge1;
    }

    public BigDecimal getStopCharge2() {
        return stopCharge2;
    }

    public void setStopCharge2(BigDecimal stopCharge2) {
        this.stopCharge2 = stopCharge2;
    }

    public BigDecimal getNoShowCharge() {
        return noShowCharge;
    }

    public void setNoShowCharge(BigDecimal noShowCharge) {
        this.noShowCharge = noShowCharge;
    }

    public BigDecimal getRoundTripCharge() {
        return roundTripCharge;
    }

    public void setRoundTripCharge(BigDecimal roundTripCharge) {
        this.roundTripCharge = roundTripCharge;
    }

    public BigDecimal getVoucherFee() {
        return voucherFee;
    }

    public void setVoucherFee(BigDecimal voucherFee) {
        this.voucherFee = voucherFee;
    }

    public BigDecimal getMinimumFare() {
        return minimumFare;
    }

    public void setMinimumFare(BigDecimal minimumFare) {
        this.minimumFare = minimumFare;
    }

    public BigDecimal getCongestionSurcharge() {
        return congestionSurcharge;
    }

    public void setCongestionSurcharge(BigDecimal congestionSurcharge) {
        this.congestionSurcharge = congestionSurcharge;
    }

    public BigDecimal getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(BigDecimal bookingFee) {
        this.bookingFee = bookingFee;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(BigDecimal fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public BigDecimal getsTCCharges() {
        return sTCCharges;
    }

    public void setsTCCharges(BigDecimal sTCCharges) {
        this.sTCCharges = sTCCharges;
    }

    public BigDecimal getSuggestedGratuity() {
        return suggestedGratuity;
    }

    public void setSuggestedGratuity(BigDecimal suggestedGratuity) {
        this.suggestedGratuity = suggestedGratuity;
    }
}
