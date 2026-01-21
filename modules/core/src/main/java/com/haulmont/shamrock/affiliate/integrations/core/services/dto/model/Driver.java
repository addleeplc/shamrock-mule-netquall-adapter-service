/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.monaco.jackson.annotations.SensitiveData;
import com.haulmont.monaco.jackson.maskers.NameMasker;
import com.haulmont.monaco.jackson.maskers.TelephoneMasker;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Driver {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("pid")
    private String pid;

    @JsonProperty("callsign")
    private String callsign;

    @JsonProperty("name")
    @SensitiveData(masker = NameMasker.class)
    private String name;

    @JsonProperty("mobile_telephone")
    @SensitiveData(masker = TelephoneMasker.class)
    private String mobileTelephone;

    @JsonProperty("vehicle")
    private Vehicle vehicle;

    //

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
