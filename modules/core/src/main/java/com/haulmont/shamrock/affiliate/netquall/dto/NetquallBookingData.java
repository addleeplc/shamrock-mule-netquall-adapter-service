/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.haulmont.bali.jackson.joda.DateTimeAdapter;
import com.haulmont.bali.jackson.joda.ModifyedDateTimeAdapter;
import com.haulmont.bali.jackson.joda.NetquallCustomFieldAdapter;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;
import com.haulmont.shamrock.affiliate.netquall.NetquallDriverLocation;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallBookingData extends AbstractJsonEntity {
    @JsonProperty("BookingNo")
    private String bookingNo;
    @JsonProperty("RefBookingNo")
    private String refBookingNo;
    @JsonProperty("VehicleCode")
    private NetquallVehicleCode vehicleCode;
    @JsonProperty("ServiceType")
    private NetquallServiceType serviceType;
    @JsonProperty("JobType")
    private NetquallJobType jobType;
    @JsonProperty("RideType")
    private NetquallRideType rideType;
    @JsonProperty("PickUpTime")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime pickUpTime;
    @JsonProperty("OnlocationTime")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime onLocationTime;
    @JsonProperty("PassengerInCarTime")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime passengerInCarTime;
    @JsonProperty("DropOffTime")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime dropOffTime;
    @JsonProperty("HourlyDuration")
    private Integer hourlyDuration;
    @JsonProperty("HoldOff")
    private Integer holdOff;
    @JsonProperty("NoOfPassengers")
    private Integer noOfPassengers;
    @JsonProperty("ChildSeats")
    private Integer childSeats;
    @JsonProperty("Pickup")
    private NetquallAddress pickup;
    @JsonProperty("DropOff")
    private NetquallAddress dropOff;
    @JsonProperty("Stops")
    private List<NetquallAddress> stops;

    @JsonProperty("PaymentData")
    private NetquallPaymentData paymentData;
    @JsonProperty("PaymentMethod")
    private NetquallPaymentMethod paymentMethod;
    @JsonProperty("ReservationCard")
    private NetquallReservationCard reservationCard;

    @JsonProperty("DateTimeLocal")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime dateTimeLocal;

    @JsonProperty("DateTimeUTC")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime dateTimeUTC;

    @JsonProperty("ReservationNotes")
    private String reservationNotes;
    @JsonProperty("ChauffeurNotes")
    private String chauffeurNotes;
    @JsonProperty("EmployeeId")
    private String employeeId;
    @JsonProperty("CostCenter")
    private String costCenter;
    @JsonProperty("ShowToDriver")
    private NetquallShowToDriver showToDriver;
    @JsonProperty("CustomFields")
    @JsonDeserialize(using = NetquallCustomFieldAdapter.Deserializer.class)
    @JsonSerialize(using = NetquallCustomFieldAdapter.Serializer.class)
    private List<NetquallCustomField> customFields;
    @JsonProperty("PassengersInfo")
    private List<NetquallPassenger> passengers;
    @JsonProperty("BookerInfo")
    private NetquallBooker booker;
    @JsonProperty("QuoteReference")
    private String quoteReference;

    //cancellation
    @JsonProperty("CancellationConfirmationNo")
    private String cancellationConfirmationNo;

    @JsonProperty("CancellationDateTimeLocal")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime cancellationDateTimeLocal;

    @JsonProperty("CancellationDateTimeUTC")
    @JsonDeserialize(using = ModifyedDateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = ModifyedDateTimeAdapter.Serializer.class)
    private DateTime cancellationDateTimeUTC;

    @JsonProperty("CancellationNotes")
    private String cancellationNotes;

    @JsonProperty("CurrencyCode")
    private String currencyCode;

    @JsonProperty("GrandTotal")
    private BigDecimal grandTotal;

    @JsonProperty("Rates")
    private NetquallRate rates;
    @JsonProperty("DetailedRates")
    private List<NetquallDetailedRate> detailedRates;

    @JsonProperty("Status")
    private NetquallDriverStatus status;
    @JsonProperty("VehicleInfo")
    private NetquallVehicle vehicle;
    @JsonProperty("DriverInfo")
    private NetquallDriverInfo driver;
    @JsonProperty("Location")
    private NetquallDriverLocation location;
    @JsonProperty("LocationInfo")
    private NetquallDriverLocation locationInfo;

    @JsonProperty("IsAccepted")
    private Boolean accepted;

    @JsonProperty("Message")
    private String message;

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getRefBookingNo() {
        return refBookingNo;
    }

    public void setRefBookingNo(String refBookingNo) {
        this.refBookingNo = refBookingNo;
    }

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

    public NetquallJobType getJobType() {
        return jobType;
    }

    public void setJobType(NetquallJobType jobType) {
        this.jobType = jobType;
    }

    public NetquallRideType getRideType() {
        return rideType;
    }

    public void setRideType(NetquallRideType rideType) {
        this.rideType = rideType;
    }

    public DateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(DateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public DateTime getOnLocationTime() {
        return onLocationTime;
    }

    public void setOnLocationTime(DateTime onLocationTime) {
        this.onLocationTime = onLocationTime;
    }

    public DateTime getPassengerInCarTime() {
        return passengerInCarTime;
    }

    public void setPassengerInCarTime(DateTime passengerInCarTime) {
        this.passengerInCarTime = passengerInCarTime;
    }

    public DateTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(DateTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public Integer getHourlyDuration() {
        return hourlyDuration;
    }

    public void setHourlyDuration(Integer hourlyDuration) {
        this.hourlyDuration = hourlyDuration;
    }

    public Integer getHoldOff() {
        return holdOff;
    }

    public void setHoldOff(Integer holdOff) {
        this.holdOff = holdOff;
    }

    public Integer getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public Integer getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(Integer childSeats) {
        this.childSeats = childSeats;
    }

    public NetquallAddress getPickup() {
        return pickup;
    }

    public void setPickup(NetquallAddress pickup) {
        this.pickup = pickup;
    }

    public NetquallAddress getDropOff() {
        return dropOff;
    }

    public void setDropOff(NetquallAddress dropOff) {
        this.dropOff = dropOff;
    }

    public List<NetquallAddress> getStops() {
        return stops;
    }

    public void setStops(List<NetquallAddress> stops) {
        this.stops = stops;
    }

    public NetquallPaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(NetquallPaymentData paymentData) {
        this.paymentData = paymentData;
    }

    public NetquallPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(NetquallPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public NetquallReservationCard getReservationCard() {
        return reservationCard;
    }

    public void setReservationCard(NetquallReservationCard reservationCard) {
        this.reservationCard = reservationCard;
    }

    public DateTime getDateTimeLocal() {
        return dateTimeLocal;
    }

    public void setDateTimeLocal(DateTime dateTimeLocal) {
        this.dateTimeLocal = dateTimeLocal;
    }

    public DateTime getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(DateTime dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public String getReservationNotes() {
        return reservationNotes;
    }

    public void setReservationNotes(String reservationNotes) {
        this.reservationNotes = reservationNotes;
    }

    public String getChauffeurNotes() {
        return chauffeurNotes;
    }

    public void setChauffeurNotes(String chauffeurNotes) {
        this.chauffeurNotes = chauffeurNotes;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public NetquallShowToDriver getShowToDriver() {
        return showToDriver;
    }

    public void setShowToDriver(NetquallShowToDriver showToDriver) {
        this.showToDriver = showToDriver;
    }

    public List<NetquallCustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<NetquallCustomField> customFields) {
        this.customFields = customFields;
    }

    public List<NetquallPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<NetquallPassenger> passengers) {
        this.passengers = passengers;
    }

    public NetquallBooker getBooker() {
        return booker;
    }

    public void setBooker(NetquallBooker booker) {
        this.booker = booker;
    }

    public String getQuoteReference() {
        return quoteReference;
    }

    public void setQuoteReference(String quoteReference) {
        this.quoteReference = quoteReference;
    }

    public String getCancellationConfirmationNo() {
        return cancellationConfirmationNo;
    }

    public void setCancellationConfirmationNo(String cancellationConfirmationNo) {
        this.cancellationConfirmationNo = cancellationConfirmationNo;
    }

    public DateTime getCancellationDateTimeLocal() {
        return cancellationDateTimeLocal;
    }

    public void setCancellationDateTimeLocal(DateTime cancellationDateTimeLocal) {
        this.cancellationDateTimeLocal = cancellationDateTimeLocal;
    }

    public DateTime getCancellationDateTimeUTC() {
        return cancellationDateTimeUTC;
    }

    public void setCancellationDateTimeUTC(DateTime cancellationDateTimeUTC) {
        this.cancellationDateTimeUTC = cancellationDateTimeUTC;
    }

    public String getCancellationNotes() {
        return cancellationNotes;
    }

    public void setCancellationNotes(String cancellationNotes) {
        this.cancellationNotes = cancellationNotes;
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

    public NetquallRate getRates() {
        return rates;
    }

    public void setRates(NetquallRate rates) {
        this.rates = rates;
    }

    public List<NetquallDetailedRate> getDetailedRates() {
        return detailedRates;
    }

    public void setDetailedRates(List<NetquallDetailedRate> detailedRates) {
        this.detailedRates = detailedRates;
    }

    public NetquallDriverStatus getStatus() {
        return status;
    }

    public void setStatus(NetquallDriverStatus status) {
        this.status = status;
    }

    public NetquallVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(NetquallVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public NetquallDriverInfo getDriver() {
        return driver;
    }

    public void setDriver(NetquallDriverInfo driver) {
        this.driver = driver;
    }

    public NetquallDriverLocation getLocation() {
        return location;
    }

    public void setLocation(NetquallDriverLocation location) {
        this.location = location;
    }

    public NetquallDriverLocation getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(NetquallDriverLocation locationInfo) {
        this.locationInfo = locationInfo;
    }

    @JsonIgnore
    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
