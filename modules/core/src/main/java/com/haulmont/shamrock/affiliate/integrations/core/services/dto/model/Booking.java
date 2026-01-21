/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.haulmont.bali.jackson.joda.DateTimeAdapter;
import com.haulmont.bali.jackson.joda.DurationAdapter;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntityWithId;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.List;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Booking extends AbstractJsonEntityWithId<UUID> {
    @JsonProperty("pid")
    private String pid;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("customer")
    private Account customer;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("payment_model")
    private PaymentModel paymentModel;

    @JsonProperty("booking_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime bookingDate;

    @JsonProperty("allocation_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime allocationDate;

    @JsonProperty("start_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime startDate;

    @JsonProperty("arrival_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime arrivalDate;

    @JsonProperty("collection_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime collectionDate;

    @JsonProperty("cancellation_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime cancellationDate;

    @JsonProperty("cancelled_with_charge")
    private Boolean cancelledWithCharge;

    @JsonProperty("date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime date;

    @JsonProperty("pickup_time_estimate")
    @JsonDeserialize(using = DurationAdapter.Deserializer.class)
    @JsonSerialize(using = DurationAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private Period pickupTimeEstimate;

    @JsonProperty("deadline_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime deadlineDate;

    @JsonProperty("stops")
    private List<Stop> stops;

    @JsonProperty("instructions")
    private List<Instruction> instructions;

    @JsonProperty("driver")
    private Driver driver;

    @JsonProperty("execution_status")
    private ExecutionStatus executionStatus;

    @JsonProperty("appearance_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime appearanceDate;

    @JsonProperty("approach_final_destination_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime approachFinalDestinationDate;

    @JsonProperty("completion_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime completionDate;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PaymentModel getPaymentModel() {
        return paymentModel;
    }

    public void setPaymentModel(PaymentModel paymentModel) {
        this.paymentModel = paymentModel;
    }

    public DateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(DateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public DateTime getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(DateTime allocationDate) {
        this.allocationDate = allocationDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(DateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public DateTime getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(DateTime collectionDate) {
        this.collectionDate = collectionDate;
    }

    public DateTime getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(DateTime cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public Boolean getCancelledWithCharge() {
        return cancelledWithCharge;
    }

    public void setCancelledWithCharge(Boolean cancelledWithCharge) {
        this.cancelledWithCharge = cancelledWithCharge;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Period getPickupTimeEstimate() {
        return pickupTimeEstimate;
    }

    public void setPickupTimeEstimate(Period pickupTimeEstimate) {
        this.pickupTimeEstimate = pickupTimeEstimate;
    }

    public DateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(DateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    public DateTime getAppearanceDate() {
        return appearanceDate;
    }

    public void setAppearanceDate(DateTime appearanceDate) {
        this.appearanceDate = appearanceDate;
    }

    public DateTime getApproachFinalDestinationDate() {
        return approachFinalDestinationDate;
    }

    public void setApproachFinalDestinationDate(DateTime approachFinalDestinationDate) {
        this.approachFinalDestinationDate = approachFinalDestinationDate;
    }

    public DateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(DateTime completionDate) {
        this.completionDate = completionDate;
    }
}
