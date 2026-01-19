/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services;

import com.haulmont.monaco.unirest.ServiceCallUtils;
import com.haulmont.monaco.unirest.UnirestCommand;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.driver_cache.Driver;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.driver_cache.res.DriverResponse;
import kong.unirest.HttpRequest;
import org.picocontainer.annotations.Component;

@Component
public class DriverCacheService {
    public static final String SERVICE_NAME = "shamrock-driver-cache-service";
    public Driver getDriverByCallSign(String callsign) {
        return ServiceCallUtils.call(
                () -> new GetDriverByCallsignCommand(callsign),
                res -> ServiceCallUtils.extract(res, DriverResponse::getDriver)
        );
    }

    private static class GetDriverByCallsignCommand extends UnirestCommand<DriverResponse> {
        private String callsign;
        public GetDriverByCallsignCommand(String callsign) {
            super(SERVICE_NAME, DriverResponse.class);
            this.callsign = callsign;
        }

        @Override
        protected HttpRequest<?> createRequest(String url, Path path) {
            return get(url, path).queryString("callsign", callsign);
        }

        @Override
        protected Path getPath() {
            return new Path("/driver");
        }
    }
}
