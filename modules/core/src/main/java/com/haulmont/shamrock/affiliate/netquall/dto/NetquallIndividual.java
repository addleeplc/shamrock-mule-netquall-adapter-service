/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.monaco.jackson.annotations.SensitiveData;
import com.haulmont.monaco.jackson.maskers.EmailMasker;
import com.haulmont.monaco.jackson.maskers.NameMasker;
import com.haulmont.monaco.jackson.maskers.TelephoneMasker;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class NetquallIndividual extends AbstractJsonEntity {
    @JsonProperty("FirstName")
    @SensitiveData(masker = NameMasker.class)
    private String firstName;
    @JsonProperty("MiddleName")
    @SensitiveData(masker = NameMasker.class)
    private String middleName;
    @JsonProperty("LastName")
    @SensitiveData(masker = NameMasker.class)
    private String lastName;
    @JsonProperty("MobilePhone")
    @SensitiveData(masker = TelephoneMasker.class)
    private String mobilePhone;
    @JsonProperty("DeskPhone")
    @SensitiveData(masker = TelephoneMasker.class)
    private String deskPhone;
    @JsonProperty("PrimaryEmail")
    @SensitiveData(masker = EmailMasker.class)
    private String primaryEmail;
    @JsonProperty("EmployeeId")
    private String employeeId;
    @JsonProperty("CostCentre")
    private String costCentre;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDeskPhone() {
        return deskPhone;
    }

    public void setDeskPhone(String deskPhone) {
        this.deskPhone = deskPhone;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCostCentre() {
        return costCentre;
    }

    public void setCostCentre(String costCentre) {
        this.costCentre = costCentre;
    }
}
