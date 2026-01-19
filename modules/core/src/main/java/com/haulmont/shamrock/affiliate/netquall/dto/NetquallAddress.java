/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.haulmont.bali.jackson.joda.DateTimeAdapter;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;
import org.joda.time.DateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallAddress extends AbstractJsonEntity {
    @JsonProperty("AddressType")
    private NetquallAddressType addressType;
    @JsonProperty("FullAddress")
    private String fullAddress;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("CrossRoad")
    private String crossRoad;
    @JsonProperty("Street")
    private String street;
    @JsonProperty("Locality")
    private String locality;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Zip")
    private String zip;
    @JsonProperty("Latitude")
    private Double latitude;
    @JsonProperty("Longitude")
    private Double longitude;
    @JsonProperty("AirportCode")
    private String airportCode;
    @JsonProperty("AirLineCode")
    private String airLineCode;
    @JsonProperty("AirLineName")
    private String airLineName;
    @JsonProperty("FlightNumber")
    private String flightNumber;
    @JsonProperty("ArrDep")
    private String arrDep;
    @JsonProperty("Terminal")
    private String terminal;
    @JsonProperty("OriginalDatetime")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime originalDatetime;
    @JsonProperty("ArrivalDatetime")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime arrivalDatetime;
    @JsonProperty("DepartureDatetime")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class)
    private DateTime departureDatetime;
    @JsonProperty("RailwayStationCode")
    private String railwayStationCode;
    @JsonProperty("TrainNumber")
    private String trainNumber;
    @JsonProperty("SeaportCode")
    private String seaportCode;
    @JsonProperty("CruiseLine")
    private String cruiseLine;
    @JsonProperty("FBOCode")
    private String fBOCode;
    @JsonProperty("TailNumber")
    private String tailNumber;
    @JsonProperty("SpecialInstructions")
    private String specialInstructions;
    @JsonProperty("Notes")
    private String notes;

    public NetquallAddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(NetquallAddressType addressType) {
        this.addressType = addressType;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrossRoad() {
        return crossRoad;
    }

    public void setCrossRoad(String crossRoad) {
        this.crossRoad = crossRoad;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirLineCode() {
        return airLineCode;
    }

    public void setAirLineCode(String airLineCode) {
        this.airLineCode = airLineCode;
    }

    public String getAirLineName() {
        return airLineName;
    }

    public void setAirLineName(String airLineName) {
        this.airLineName = airLineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getArrDep() {
        return arrDep;
    }

    public void setArrDep(String arrDep) {
        this.arrDep = arrDep;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public DateTime getOriginalDatetime() {
        return originalDatetime;
    }

    public void setOriginalDatetime(DateTime originalDatetime) {
        this.originalDatetime = originalDatetime;
    }

    public DateTime getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(DateTime arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public DateTime getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(DateTime departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public String getRailwayStationCode() {
        return railwayStationCode;
    }

    public void setRailwayStationCode(String railwayStationCode) {
        this.railwayStationCode = railwayStationCode;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getSeaportCode() {
        return seaportCode;
    }

    public void setSeaportCode(String seaportCode) {
        this.seaportCode = seaportCode;
    }

    public String getCruiseLine() {
        return cruiseLine;
    }

    public void setCruiseLine(String cruiseLine) {
        this.cruiseLine = cruiseLine;
    }

    public String getfBOCode() {
        return fBOCode;
    }

    public void setfBOCode(String fBOCode) {
        this.fBOCode = fBOCode;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

