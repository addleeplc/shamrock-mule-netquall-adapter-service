/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.driver_cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.haulmont.bali.jackson.joda.LocalDateAdapter;
import com.haulmont.monaco.jackson.annotations.SensitiveData;
import com.haulmont.monaco.jackson.maskers.EmailMasker;
import com.haulmont.monaco.jackson.maskers.NameMasker;
import com.haulmont.monaco.jackson.maskers.TelephoneMasker;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntityWithId;
import org.joda.time.LocalDate;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Driver extends AbstractJsonEntityWithId<UUID> {
    @JsonProperty("pid")
    private String pid;

    @JsonProperty("number")
    private String number;

    @JsonProperty("callsign")
    private String callsign;

    @JsonProperty("full_name")
    @SensitiveData(masker = NameMasker.class)
    private String fullName;

    @JsonProperty("name")
    @SensitiveData(masker = NameMasker.class)
    private String name;

    @JsonProperty("first_name")
    @SensitiveData(masker = NameMasker.class)
    private String firstName;

    @JsonProperty("last_name")
    @SensitiveData(masker = NameMasker.class)
    private String lastName;

    @JsonProperty("middle_name")
    @SensitiveData(masker = NameMasker.class)
    private String middleName;

    @JsonProperty("gender")
    @SensitiveData
    private String gender;

    @JsonProperty("date_of_birth")
    @JsonSerialize(using = LocalDateAdapter.Serializer.class)
    @JsonDeserialize(using = LocalDateAdapter.Deserializer.class)
    private LocalDate dateOfBirth;

    @JsonProperty("telephone")
    @SensitiveData(masker = TelephoneMasker.class)
    private String telephone;

    @JsonProperty("mobile_telephone")
    @SensitiveData(masker = TelephoneMasker.class)
    private String mobileTelephone;

    @JsonProperty("email")
    @SensitiveData(masker = EmailMasker.class)
    private String email;

    @JsonProperty("grade")
    private String grade;

    @JsonProperty("driving_licence")
    @SensitiveData
    private String drivingLicence;

    @JsonProperty("pco_licence")
    @SensitiveData
    private String pcoLicence;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getPcoLicence() {
        return pcoLicence;
    }

    public void setPcoLicence(String pcoLicence) {
        this.pcoLicence = pcoLicence;
    }
}
