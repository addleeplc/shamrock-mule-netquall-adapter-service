/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.services;

import com.haulmont.shamrock.affiliate.integrations.core.model.Credentials;
import com.haulmont.shamrock.affiliate.integrations.core.unirest.AbstractCredentialsBasedUnirestCommand;
import com.haulmont.shamrock.affiliate.netquall.IntegrationContext;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallChannelType;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallCredentials;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeaderMethod;
import com.haulmont.shamrock.affiliate.netquall.dto.req.*;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallRefResponse;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallResponse;
import kong.unirest.HttpRequest;
import org.picocontainer.annotations.Component;

import javax.ws.rs.core.HttpHeaders;

@Component
public class NetquallOutboundService {
    private static final String SERVICE_NAME = "NetquallOutbound";


    public NetquallRefResponse postStatusUpdates(IntegrationContext ctx, NetquallStatusUpdateRequest statusUpdateRequest) {
        return new StatusUpdateCommand(ctx, statusUpdateRequest).execute();
    }

    public NetquallRefResponse postDriverLocation(IntegrationContext ctx, NetquallDriverLocationRequest locationRequest) {
        return new DriverLocationCommand(ctx, locationRequest).execute();
    }

    public NetquallRefResponse postFinalCloseout(IntegrationContext ctx, NetquallFinalCloseoutRequest finalCloseoutRequest) {
        return new FinalCloseoutCommand(ctx, finalCloseoutRequest).execute();
    }

    public NetquallRefResponse postRequestOverride(IntegrationContext ctx, NetquallRequestOverrideRequest overrideRequest) {
        return new RequestOverrideCommand(ctx, overrideRequest).execute();
    }

    private static class DriverLocationCommand extends NetquallCommand {
        NetquallDriverLocationRequest request;

        public DriverLocationCommand(IntegrationContext context, NetquallDriverLocationRequest request) {
            super(context, false, NetquallHeaderMethod.DRIVER_LOCATION, request);
            this.request = request;
        }
    }

    private static class FinalCloseoutCommand extends NetquallCommand {
        public FinalCloseoutCommand(IntegrationContext context, NetquallRequest request) {
            super(context, NetquallHeaderMethod.FINAL_CLOSEOUT, request);
        }
    }

    private static class RequestOverrideCommand extends NetquallCommand {
        public RequestOverrideCommand(IntegrationContext context, NetquallRequestOverrideRequest request) {
            super(context, NetquallHeaderMethod.REQUEST_OVERRIDE, request);
        }
    }

    private static class StatusUpdateCommand extends NetquallCommand {

        public StatusUpdateCommand(IntegrationContext context, NetquallRequest request) {
            super(context, NetquallHeaderMethod.STATUS_UPDATES, request);
        }
    }

    private static class NetquallCommand extends AbstractCredentialsBasedUnirestCommand<NetquallRefResponse, Credentials.Basic> {
        private NetquallRequest request;
        private NetquallHeaderMethod method;

        public NetquallCommand(IntegrationContext context, NetquallHeaderMethod method, NetquallRequest request) {
            this(context, true, method, request);
        }

        public NetquallCommand(IntegrationContext context, boolean auditEnabled, NetquallHeaderMethod method, NetquallRequest request) {
            super(SERVICE_NAME, true, auditEnabled, () -> context.getIntegrationSettings().getNetquallCredentials(), NetquallRefResponse.class);
            this.request = request;
            this.method = method;
        }

        @Override
        protected HttpRequest<?> createRequest(String url, Path path) {
            request.setCredentials(new NetquallCredentials(this.credentialsSupplier.get().getUser(), this.credentialsSupplier.get().getPassword()));
            request.getHeader().setMethod(method);
            request.getHeader().setChannelType(NetquallChannelType.CORPORATE_NETWORK);
            return post(url, path)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(request);

        }

        @Override
        protected Path getPath() {
            return new Path("/statuses");
        }
    }

}
