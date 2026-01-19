/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.monaco.jackson.annotations.SensitiveData;
import com.haulmont.monaco.jackson.maskers.CreditCardNumberMasker;
import com.haulmont.monaco.jackson.maskers.NameMasker;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallPaymentData extends AbstractJsonEntity {
    @JsonProperty("CardHolderName")
    @SensitiveData(masker = NameMasker.class)
    private String cardHolderName;
    @JsonProperty("CardType")
    private String cardType;
    @JsonProperty("CardNumber")
    @SensitiveData(masker = CreditCardNumberMasker.class)
    private String cardNumber;
    @JsonProperty("CardCompany")
    private String cardCompany;
    @JsonProperty("CardExpiryMonth")
    @SensitiveData
    private Integer cardExpiryMonth;
    @JsonProperty("CardExpiryYear")
    @SensitiveData
    private Integer cardExpiryYear;
    @JsonProperty("AVSAddress")
    private String aVSAddress;
    @JsonProperty("ZipCode")
    private String zipCode;

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public Integer getCardExpiryMonth() {
        return cardExpiryMonth;
    }

    public void setCardExpiryMonth(Integer cardExpiryMonth) {
        this.cardExpiryMonth = cardExpiryMonth;
    }

    public Integer getCardExpiryYear() {
        return cardExpiryYear;
    }

    public void setCardExpiryYear(Integer cardExpiryYear) {
        this.cardExpiryYear = cardExpiryYear;
    }

    public String getaVSAddress() {
        return aVSAddress;
    }

    public void setaVSAddress(String aVSAddress) {
        this.aVSAddress = aVSAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
