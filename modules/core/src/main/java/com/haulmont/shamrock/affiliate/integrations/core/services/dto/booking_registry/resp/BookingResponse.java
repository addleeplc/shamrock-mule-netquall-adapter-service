/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.booking_registry.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.monaco.response.Response;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.model.Booking;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse extends Response {

    @JsonProperty("booking")
    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
