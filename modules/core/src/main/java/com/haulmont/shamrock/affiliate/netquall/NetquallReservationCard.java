/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallReservationCard extends AbstractJsonEntity {
    @JsonProperty("CardHolderName")
    private String cardHolderName;
    @JsonProperty("CardType")
    private String cardType;
    @JsonProperty("CardNumber")
    private String cardNumber;
    @JsonProperty("CardCompany")
    private String cardCompany;
    @JsonProperty("CardExpiryMonth")
    private String cardExpiryMonth;
    @JsonProperty("CardExpiryYear")
    private String cardExpiryYear;
    @JsonProperty("AVSAddress")
    private String aVSAddress;
    @JsonProperty("ZipCode")
    private String zipCode;
}
