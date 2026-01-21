/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services;

import com.haulmont.monaco.unirest.ServiceCallUtils;
import com.haulmont.monaco.unirest.UnirestCommand;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.booking_registry.resp.BookingResponse;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.model.Booking;
import kong.unirest.HttpRequest;
import org.picocontainer.annotations.Component;

import java.util.Collections;
import java.util.UUID;

@Component
public class BookingRegistryService {
    private static final String SERVICE_NAME = "shamrock-booking-registry-service";

    public Booking getExecutionStatus(UUID bookingId) {
        return ServiceCallUtils.call(
                () -> new GetBookingExecutionStatusCommand(bookingId),
                response -> ServiceCallUtils.extract(response, BookingResponse::getBooking));
    }

    private static class GetBookingExecutionStatusCommand extends UnirestCommand<BookingResponse> {
        private final UUID bookingId;

        public GetBookingExecutionStatusCommand(UUID bookingId) {
            super(SERVICE_NAME, BookingResponse.class);
            this.bookingId = bookingId;
        }

        @Override
        protected HttpRequest<?> createRequest(String url, Path path) {
            return get(url, path);
        }

        @Override
        protected Path getPath() {
            return new Path("/bookings/{booking_id}/execution_status", Collections.singletonMap("booking_id", bookingId));
        }
    }

}
