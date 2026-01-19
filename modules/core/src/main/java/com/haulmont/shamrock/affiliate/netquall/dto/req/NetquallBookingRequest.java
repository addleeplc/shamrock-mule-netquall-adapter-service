/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.req;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallBookingData;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonDeserialize() //override custom deserializer to prevent stack overflow
public class NetquallBookingRequest extends NetquallRequest {
    @JsonProperty("BookingData")
    private NetquallBookingData bookingData;

    public NetquallBookingData getBookingData() {
        return bookingData;
    }

    public void setBookingData(NetquallBookingData bookingData) {
        this.bookingData = bookingData;
    }
}
