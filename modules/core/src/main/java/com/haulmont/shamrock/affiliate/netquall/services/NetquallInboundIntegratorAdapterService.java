package com.haulmont.shamrock.affiliate.netquall.services;

import com.haulmont.monaco.ServiceException;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.*;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.Error;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.AbstractVendorReferenceRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.AsyncRespRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.BookingRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.CancelBookingRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.BookingResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.CancelBookingResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.EstimatesResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.Response;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.exceptions.AffiliatesApiException;
import com.haulmont.shamrock.affiliate.integrations.core.cache.AffiliateAuthCache;
import com.haulmont.shamrock.affiliate.integrations.core.services.AffiliateIntegrationService;
import com.haulmont.shamrock.affiliate.netquall.IntegrationContext;
import com.haulmont.shamrock.affiliate.netquall.dto.*;
import com.haulmont.shamrock.affiliate.netquall.dto.req.*;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallBookingResponse;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallCancelResponse;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallQuoteResponse;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallResponse;
import com.haulmont.shamrock.affiliate.netquall.mapping.BookingConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.picocontainer.annotations.Component;
import org.picocontainer.annotations.Inject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class NetquallInboundIntegratorAdapterService {

    @Inject
    private AffiliateIntegrationService affiliateIntegrationService;
    @Inject
    private AffiliateAuthCache affiliateAuthCache;

    protected <A_REQ extends AbstractVendorReferenceRequest, A_R extends Response, INT_R extends NetquallResponse>
    INT_R doCall(Supplier<A_REQ> extRequestToAffiliateConverter, Function<A_REQ, A_R> affiliateBean, Function<A_R, INT_R> affiliateToVendorConverter) {
        return affiliateToVendorConverter.apply(affiliateBean.apply(extRequestToAffiliateConverter.get()));
    }

    private void validateAffiliateResponse(Response response) throws ServiceException {
        if (!Objects.equals(response.getError().getCode(), 0)) {
            throw new AffiliatesApiException(response.getError());
        }
    }


//    private VendorReference getVendorReference(NetquallRequest netquallRequest) {
//        return getVendorReference(netquallRequest, null);
//    }

//    private VendorReference getVendorReference(NetquallRequest netquallRequest) {
//        Consumer<AbstractReference> runnable = null;
//        if (netquallRequest instanceof NetquallBookingRequest) {
//            NetquallBookingRequest bookingRequest = (NetquallBookingRequest) netquallRequest;
//            runnable =reference -> {
//                NetquallBookingData bookingData = bookingRequest.getBookingData();
//                if (CollectionUtils.isNotEmpty(reference.getCustomerReference().getCompanyReferences()))
//                    reference.getCustomerReference().setCompanyReferences(new ArrayList<>());
//                reference.getCustomerReference().getCompanyReferences().add(new CompanyReference("VEHICLE_TYPE", bookingData.getVehicleCode().getCode()));
//            };
//        }
//        return getVendorReference(netquallRequest, runnable);
//    }
//
//    private <T extends NetquallRequest> VendorReference getVendorReference(T netquallRequest, Consumer<AbstractReference> runnable) {
//        VendorReference vendorReference = new VendorReference();
//        vendorReference.setNumber(netquallRequest.getHeader().getSenderBookingNumber());
//        //vendorReference.setId(netquallRequest.getHeader().getSenderCode());
//        vendorReference.setCustomerReference(new CustomerReference());
//        vendorReference.getCustomerReference().setAccount("1000");//TODO
//        if (runnable != null) {
//            runnable.accept(vendorReference);
//        }
//        return vendorReference;
//    }
//
//    private BookingReference getBookingReference(NetquallRequest netquallRequest) {
//        BookingReference bookingReference = null;
//        if (StringUtils.isNotBlank(netquallRequest.getHeader().getReceiverBookingNumber())) {
//            bookingReference = new BookingReference();
//            bookingReference.setId(netquallRequest.getHeader().getReceiverBookingNumber());
//            //bookingReference.setId(netquallRequest.getHeader().getReceiverCode());
//        }
//        return bookingReference;
//    }

    private BookingRequest convert(IntegrationContext ctx, NetquallBookingRequest netquallBookingRequest) {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setBookingReference(BookingConverter.getBookingReference(netquallBookingRequest));
        bookingRequest.setVendorReference(BookingConverter.getVendorReference(ctx, netquallBookingRequest));
        bookingRequest.setBooking(BookingConverter.convert(ctx, netquallBookingRequest));
        return bookingRequest;
    }

    private AsyncRespRequest convert(IntegrationContext ctx, NetquallAuditRequest netquallAuditRequest) {
        AsyncRespRequest bookingRequest = new AsyncRespRequest();
        bookingRequest.setBookingReference(BookingConverter.getBookingReference(netquallAuditRequest));
        bookingRequest.setVendorReference(BookingConverter.getVendorReference(ctx, netquallAuditRequest));
        if (BooleanUtils.isTrue(netquallAuditRequest.getBookingData().isAccepted()))
            bookingRequest.setError(new Error(ErrorCode.REJECT.getCode(), netquallAuditRequest.getBookingData().getMessage()));
        else
            bookingRequest.setError(new Error(ErrorCode.OK));
        return bookingRequest;
    }

    private CancelBookingRequest convert(IntegrationContext ctx, NetquallCancelRequest netquallBookingRequest) {
        CancelBookingRequest bookingRequest = new CancelBookingRequest();
        bookingRequest.setBookingReference(BookingConverter.getBookingReference(netquallBookingRequest));
        bookingRequest.setVendorReference(BookingConverter.getVendorReference(ctx, netquallBookingRequest));
        return bookingRequest;
    }

    private NetquallQuoteResponse convertToEstimateRes(IntegrationContext ctx, EstimatesResponse estimatesResponse) {
        validateAffiliateResponse(estimatesResponse);
        return BookingConverter.convertToEstimateRes(ctx, estimatesResponse);
    }

    private NetquallBookingResponse convertToBookingRes(IntegrationContext ctx, NetquallHeaderMethod headerMethod, BookingResponse bookingResponse) {
        validateAffiliateResponse(bookingResponse);
        return BookingConverter.convertToVendorRes(ctx, headerMethod, bookingResponse);
    }

    private NetquallCancelResponse convertToCancelRes(IntegrationContext ctx, CancelBookingResponse cancelBookingResponse) {
        validateAffiliateResponse(cancelBookingResponse);
        return BookingConverter.convertToVendorRes(ctx, cancelBookingResponse);
    }

    private NetquallResponse convertToRes(IntegrationContext ctx, Response response) {
        validateAffiliateResponse(response);
        return BookingConverter.convertToVendorRes(ctx, response);
    }

    public NetquallQuoteResponse getQuote(IntegrationContext ctx, NetquallQuoteRequest quoteRequest) {
        return doCall(
                () -> convert(ctx, quoteRequest),
                aReq -> affiliateIntegrationService.evaluateEstimates(ctx, aReq),
                aRes -> convertToEstimateRes(ctx, aRes)
        );
    }

    public NetquallBookingResponse createOrAmendBooking(IntegrationContext ctx, NetquallHeaderMethod headerMethod, NetquallBookingRequest bookingRequest) {
        return doCall(
                () -> convert(ctx, bookingRequest),
                aReq -> aReq.getBookingReference() == null || StringUtils.isBlank(aReq.getBookingReference().getId()) ?
                        affiliateIntegrationService.createBooking(ctx, aReq) : affiliateIntegrationService.updateBooking(ctx, aReq),
                aRes -> convertToBookingRes(ctx, headerMethod, aRes)
        );
    }

    public NetquallResponse cancelBooking(IntegrationContext ctx, NetquallCancelRequest netquallProviderCancelRequest) {
        return doCall(
                () -> convert(ctx, netquallProviderCancelRequest),
                aReq -> affiliateIntegrationService.cancelBooking(ctx, aReq),
                aRes -> convertToCancelRes(ctx, aRes)
        );
    }

    public NetquallResponse postAudit(IntegrationContext ctx, NetquallAuditRequest auditRequest) {
        return doCall(
                () -> convert(ctx, auditRequest),
                aReq -> affiliateIntegrationService.postAsyncReceiptRespRequest(ctx, aReq),
                aRes -> convertToRes(ctx, aRes)
        );
    }

    public NetquallResponse postStatusUpdatesCallback(IntegrationContext ctx, NetquallStatusUpdatesCallback updatesCallback) {
        NetquallResponse response = new NetquallResponse();
        response.setMessage("Ok");
        return response;
    }

    public NetquallResponse postPaymentStatusCallback(IntegrationContext ctx, NetquallPaymentStatusCallback paymentStatusCallback) {
        NetquallResponse response = new NetquallResponse();
        response.setMessage("Ok");
        return response;
    }

}
