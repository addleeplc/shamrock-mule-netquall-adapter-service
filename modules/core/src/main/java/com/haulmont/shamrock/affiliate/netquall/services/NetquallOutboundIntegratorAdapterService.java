package com.haulmont.shamrock.affiliate.netquall.services;

import com.haulmont.shamrock.affiliate.integrations.core.IntegrationContext;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.IntegrationAdapter;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.AsyncStatus;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.ErrorCode;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.BookingStatusRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.DriverPositionRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.ReceiptRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.AsyncResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.Response;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.exceptions.AffiliatesApiException;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallErrorType;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeaderMethod;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallStatus;
import com.haulmont.shamrock.affiliate.netquall.dto.req.NetquallRequest;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallRefResponse;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallResponse;
import com.haulmont.shamrock.affiliate.netquall.mapping.BookingConverter;
import org.picocontainer.annotations.Component;
import org.picocontainer.annotations.Inject;

import java.util.Objects;
import java.util.function.Function;

@Component
public class NetquallOutboundIntegratorAdapterService implements IntegrationAdapter {
    @Inject
    private NetquallOutboundService netquallOutboundService;

    @FunctionalInterface
    private interface VendorFunction<T, R, C extends IntegrationContext> {
        R apply(C ctx, T r);
    }

    private <A_R extends Response, S_REQ extends NetquallRequest, S_RES extends NetquallResponse>
    A_R doCall(
            IntegrationContext ctx,
            Function<com.haulmont.shamrock.affiliate.netquall.IntegrationContext, S_REQ> request,
            VendorFunction<S_REQ, S_RES, com.haulmont.shamrock.affiliate.netquall.IntegrationContext> function,
            Function<S_RES, A_R> vendorToAffiliateConverter
    ) {
        com.haulmont.shamrock.affiliate.netquall.IntegrationContext context = new com.haulmont.shamrock.affiliate.netquall.IntegrationContext(ctx);
        //SixtOutboundService.AuthSupplier authSupplier = getAuthSupplier(context);
        return vendorToAffiliateConverter.apply(
                function.apply(
                        context,
                        request.apply(context)
                )
        );
    }

    private Response buildAffiliateResponse(NetquallResponse netquallRefResponse) {
        if (Objects.equals(netquallRefResponse.getStatus(), NetquallStatus.SUCCESS)) {
            return new Response();
        } else {
            throw new AffiliatesApiException(ErrorCode.UNKNOWN, netquallRefResponse.getMessage());
        }
    }

    private Response buildAffiliateResponse(NetquallRefResponse netquallRefResponse) {
        if (Objects.equals(netquallRefResponse.getStatus(), NetquallStatus.SUCCESS)) {
            if (Objects.equals(netquallRefResponse.getHeader().getMethod(), NetquallHeaderMethod.REQUEST_OVERRIDE)
                || Objects.equals(netquallRefResponse.getHeader().getMethod(), NetquallHeaderMethod.FINAL_CLOSEOUT)) {
                return new AsyncResponse(AsyncStatus.QUEUED);
            } else {
                return new Response();
            }
        } else {
            if (netquallRefResponse.getResponseMessage() != null) {
                if (Objects.equals(netquallRefResponse.getResponseMessage().getErrorType(), NetquallErrorType.REJECTED))
                    throw new AffiliatesApiException(ErrorCode.REJECT, netquallRefResponse.getMessage());
            }
            throw new AffiliatesApiException(ErrorCode.UNKNOWN, netquallRefResponse.getMessage());
        }
    }

    @Override
    public Response postStatus(IntegrationContext ctx, BookingStatusRequest request) {
        return doCall(ctx,
                c -> BookingConverter.convert(c, request),
                (c, netquallReq) -> netquallOutboundService.postStatusUpdates(c, netquallReq),
                this::buildAffiliateResponse
        );
    }

    @Override
    public Response postPosition(IntegrationContext ctx, DriverPositionRequest request) {
        return doCall(ctx,
                c -> BookingConverter.convert(c, request),
                (c, netquallReq) -> netquallOutboundService.postDriverLocation(c, netquallReq),
                this::buildAffiliateResponse
        );
    }

    @Override
    public Response postReceipt(IntegrationContext ctx, ReceiptRequest request) {
        return doCall(ctx,
                c -> BookingConverter.convert(c, request),
                (c, netquallReq) -> netquallOutboundService.postFinalCloseout(c, netquallReq),
                res -> buildResponseWithOverride(ctx, request, res)
        );
    }

    private Response postOverride(IntegrationContext ctx, ReceiptRequest request) {
        return doCall(ctx,
                c -> BookingConverter.convertToOverrideReq(c, request),
                (c, netquallReq) -> netquallOutboundService.postRequestOverride(c, netquallReq),
                this::buildAffiliateResponse
        );
    }

    private Response buildResponseWithOverride(IntegrationContext ctx, ReceiptRequest request, NetquallRefResponse netquallRefResponse) {
        try {
            return buildAffiliateResponse(netquallRefResponse);
        } catch (AffiliatesApiException ex) {
            if (Objects.equals(ex.getError().getCode(), ErrorCode.REJECT.getCode())) {
                return postOverride(ctx, request);
            }
            throw ex;
        }
    }
}
