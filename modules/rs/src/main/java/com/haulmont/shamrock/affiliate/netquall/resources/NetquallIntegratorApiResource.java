/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.resources;

import com.haulmont.bali.jackson.JsonSerializer;
import com.haulmont.monaco.AppContext;
import com.haulmont.monaco.ServiceException;
import com.haulmont.monaco.response.ErrorCode;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.affiliates_registry.NetquallApiAdapterSettings;
import com.haulmont.shamrock.affiliate.integrations.rs.AbstractIntegrationResource;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.Authenticated;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.Credentials;
import com.haulmont.shamrock.affiliate.netquall.IntegrationContext;
import com.haulmont.shamrock.affiliate.netquall.auth.NetquallAuthCredentialsSupplier;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeader;
import com.haulmont.shamrock.affiliate.netquall.dto.req.*;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallResponse;
import com.haulmont.shamrock.affiliate.netquall.services.NetquallInboundIntegratorAdapterService;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Path("/1.0")
@Authenticated(credentials = @Credentials(supplier = NetquallAuthCredentialsSupplier.class), name = NetquallApiAdapterSettings.ADAPTER_CREDENTIALS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NetquallIntegratorApiResource extends AbstractIntegrationResource {

    private NetquallInboundIntegratorAdapterService adapterService;

    public NetquallIntegratorApiResource() {
        adapterService = AppContext.getBean(NetquallInboundIntegratorAdapterService.class);
    }

    @POST
    @Path("/endpoints")
    public NetquallResponse postRequest(/*InputStream requestBodyStream*/ NetquallRequest request) throws IOException {
//        String body = IOUtils.toString(requestBodyStream, StandardCharsets.UTF_8);
//        NetquallRequest request = JsonSerializer.getInstance().readValue(body, NetquallRequest.class);
//        String body = JsonSerializer.getInstance().writeValue(request);
        NetquallHeader header = request.getHeader();
        switch (header.getMethod()) {
            case GET_QUOTE:
                return adapterService.getQuote(getIntegrationContext(), (NetquallQuoteRequest) request);
            case RESERVE:
            case PROVIDER_RESERVATION_UPDATE:
                return adapterService.createOrAmendBooking(getIntegrationContext(), header.getMethod(), (NetquallBookingRequest) request);
            case CANCEL_RESERVATION:
            case PROVIDER_CANCEL:
                return adapterService.cancelBooking(getIntegrationContext(), (NetquallProviderCancelRequest) request);
            case AUDIT_RESPONSE:
                return adapterService.postAudit(getIntegrationContext(), (NetquallAuditRequest) request);
            case PAYMENT_STATUS_CALLBACK:
                return adapterService.postPaymentStatusCallback(getIntegrationContext(), (NetquallPaymentStatusCallback) request);
            case STATUS_UPDATES_CALLBACK:
                return adapterService.postStatusUpdatesCallback(getIntegrationContext(), (NetquallStatusUpdatesCallback) request);
        }
        throw new ServiceException(ErrorCode.BAD_REQUEST);
    }

    protected IntegrationContext getIntegrationContext() {
        return new IntegrationContext(getUserPrincipal().getAffiliate());
    }

}
