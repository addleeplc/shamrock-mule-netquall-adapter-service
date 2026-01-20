/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.mapping;

import com.google.common.collect.BiMap;
import com.haulmont.bali.lang.BooleanUtils;
import com.haulmont.monaco.AppContext;
import com.haulmont.monaco.RequestContext;
import com.haulmont.monaco.ServiceException;
import com.haulmont.monaco.response.ErrorCode;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.*;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.BookingReferenceRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.BookingStatusRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.DriverPositionRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.req.ReceiptRequest;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.BookingResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.CancelBookingResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.EstimatesResponse;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.resp.Response;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.exceptions.AffiliatesApiException;
import com.haulmont.shamrock.affiliate.integrations.core.services.DriverCacheService;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.affiliates_registry.NetquallApiAdapterSettings;
import com.haulmont.shamrock.affiliate.integrations.core.utils.AffiliateApiUtils;
import com.haulmont.shamrock.affiliate.netquall.IntegrationContext;
import com.haulmont.shamrock.affiliate.netquall.NetquallDriverLocation;
import com.haulmont.shamrock.affiliate.netquall.dto.*;
import com.haulmont.shamrock.affiliate.netquall.dto.req.*;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.YearMonth;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BookingConverter {
    private static void prepareGeneralState(NetquallBookingRequest request, BookingReferenceRequest affiliateRequest) {
        String accountName = AffiliateApiUtils.getReferenceValue(affiliateRequest.getVendorReference().getCustomerReference().getCompanyReferences(), "ACCOUNT_NAME");
        String[] ss = Objects.requireNonNull(accountName).split("\\|");
        request.setHeader(new NetquallHeader());
        NetquallHeader header = request.getHeader();
        header.setChannelType(NetquallChannelType.CORPORATE_NETWORK);
        header.setSenderBookingNumber(affiliateRequest.getBookingReference().getId());
        header.setSenderCode(ss[1]);
        header.setReceiverBookingNumber(affiliateRequest.getVendorReference().getId());
        header.setReceiverCode(ss[0]);

        request.setBookingData(new NetquallBookingData());
        NetquallBookingData bookingData = request.getBookingData();

        bookingData.setBookingNo(affiliateRequest.getBookingReference().getId());
        bookingData.setRefBookingNo(affiliateRequest.getVendorReference().getId());
        bookingData.setDateTimeLocal(DateTime.now());
    }

    public static BookingReference getBookingReference(NetquallRequest netquallRequest) {
        BookingReference bookingReference = null;
        if (StringUtils.isNotBlank(netquallRequest.getHeader().getReceiverBookingNumber())) {
            bookingReference = new BookingReference();
            bookingReference.setId(netquallRequest.getHeader().getReceiverBookingNumber());
            //bookingReference.setId(netquallRequest.getHeader().getReceiverCode());
        }
        return bookingReference;
    }

    public static VendorReference getVendorReference(IntegrationContext ctx, NetquallRequest netquallRequest) {
        Consumer<AbstractReference> runnable = null;
        if (netquallRequest instanceof NetquallBookingRequest) {
            NetquallBookingRequest bookingRequest = (NetquallBookingRequest) netquallRequest;
            runnable =reference -> {
                NetquallBookingData bookingData = bookingRequest.getBookingData();
                if (CollectionUtils.isNotEmpty(reference.getCustomerReference().getCompanyReferences()))
                    reference.getCustomerReference().setCompanyReferences(new ArrayList<>());
                if (bookingData.getVehicleCode() != null)
                    reference.getCustomerReference().getCompanyReferences().add(new CompanyReference("VEHICLE_TYPE", bookingData.getVehicleCode().getCode()));
                reference.getCustomerReference().getCompanyReferences().add(new CompanyReference("ACCOUNT_NAME", String.format("%s|%s", netquallRequest.getHeader().getSenderCode(), netquallRequest.getHeader().getReceiverCode())));
            };
        }
        return getVendorReference(ctx, netquallRequest, runnable);
    }

    private static  <T extends NetquallRequest> VendorReference getVendorReference(IntegrationContext ctx, T netquallRequest, Consumer<AbstractReference> runnable) {
        VendorReference vendorReference = new VendorReference();
        vendorReference.setNumber(netquallRequest.getHeader().getSenderBookingNumber());
        vendorReference.setId(netquallRequest.getHeader().getSenderBookingNumber());
        //vendorReference.setId(netquallRequest.getHeader().getSenderCode());
        NetquallApiAdapterSettings integrationSettings = ctx.getIntegrationSettings();
        String key = String.format("%s|%s", netquallRequest.getHeader().getSenderCode(), netquallRequest.getHeader().getReceiverCode());
        String account = integrationSettings.getAccountMap().get(key);
        if  (StringUtils.isBlank(account)) {
            throw new AffiliatesApiException(
                    com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.ErrorCode.INCORRECT_PARAMETERS,
                    String.format("Appropriate matched account for combination senderCode|receiverCode: %s wasn't found", key)
            );
        }
        vendorReference.setCustomerReference(new CustomerReference());
        vendorReference.getCustomerReference().setAccount(account);
        if (runnable != null) {
            runnable.accept(vendorReference);
        }
        return vendorReference;
    }

    private static NetquallDriverStatus convert(Status status) {
        NetquallDriverStatus driverStatus = NetquallDriverStatus.UNKNOWN;
        switch (status) {
            case BOOKED:
                driverStatus = NetquallDriverStatus.CONFIRMED;
                break;
            case ALLOCATED:
                driverStatus = NetquallDriverStatus.ALLOCATED;
                break;
            case ON_WAY:
                driverStatus = NetquallDriverStatus.ON_ROAD;
                break;
            case AT_PICKUP:
                driverStatus = NetquallDriverStatus.ON_LOCATION;
                break;
            case POB:
                driverStatus = NetquallDriverStatus.ON_BOARD;
                break;
            case CANCELLED_WITH_CHARGE:
                driverStatus = NetquallDriverStatus.COA;
                break;
            case DONE:
                driverStatus = NetquallDriverStatus.DONE;
                break;
            default:
                throw new AffiliatesApiException(com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.ErrorCode.UNSUPPORTED_OPERATION, String.format("Unsupported status %s", status));
        }
        return  driverStatus;
    }

    private static NetquallDriverInfo convert(Driver driver) {
        if (driver == null) return null;

//        DriverCacheService driverCacheService = AppContext.getBean(DriverCacheService.class);
//        com.haulmont.shamrock.affiliate.integrations.core.services.dto.driver_cache.Driver driverDto = driverCacheService.getDriverByCallSign(driver.getCallsign());

        NetquallDriverInfo driverInfo = new NetquallDriverInfo();
        driverInfo.setFirstName(driver.getName());
        driverInfo.setDriverID(driver.getNumber());
        driverInfo.setPhoneNumber(driver.getMobile());
//            if (StringUtils.isNotBlank(request.getDriver().getName())) {
//                String[] ss = request.getDriver().getName().split(", ", 2);
//                driverInfo.setFirstName(ss[0]);
//                if (ss.length > 1)
//                    driverInfo.setLastName(ss[1]);
//            }
        return driverInfo;
    }
    
    public static NetquallStatusUpdateRequest convert(IntegrationContext ctx, BookingStatusRequest request) {
        NetquallStatusUpdateRequest updateRequest = new NetquallStatusUpdateRequest();
        prepareGeneralState(updateRequest, request);
        updateRequest.getBookingData().setStatus(convert(request.getStatus()));
        updateRequest.getBookingData().setDriver(convert(request.getDriver()));
        if (request.getVehicle() != null) {
            updateRequest.getBookingData().setVehicle(new NetquallVehicle());
            NetquallVehicle vehicle = updateRequest.getBookingData().getVehicle();
            vehicle.setModel(request.getVehicle().getModel());
            vehicle.setManufacturer(request.getVehicle().getMake());
            vehicle.setPlateNumber(request.getVehicle().getRegistrationPlate());
        }
//        if (request.getLocation() != null) {
//            updateRequest.getBookingData().setLocation(new NetquallDriverLocation());
//            NetquallDriverLocation location = updateRequest.getBookingData().getLocation();
//            location.setLatitude(request.getLocation().getLat());
//            location.setLongitude(request.getLocation().getLon());
//            //TODO do they need full details of address?
//        }

        return updateRequest;
    }

    public static NetquallDriverLocationRequest convert(IntegrationContext ctx, DriverPositionRequest request) {
        NetquallDriverLocationRequest locationRequest = new NetquallDriverLocationRequest();
        prepareGeneralState(locationRequest, request);
        locationRequest.getBookingData().setLocationInfo(new NetquallDriverLocation());
        locationRequest.getBookingData().getLocationInfo().setLatitude(request.getLocation().getLat());
        locationRequest.getBookingData().getLocationInfo().setLongitude(request.getLocation().getLon());
        locationRequest.getBookingData().setStatus(convert(request.getStatus()));
        return locationRequest;
    }

    public static NetquallRequestOverrideRequest convertToOverrideReq(IntegrationContext ctx, ReceiptRequest request) {
        NetquallRequestOverrideRequest overrideRequest = new NetquallRequestOverrideRequest();
        prepareGeneralState(overrideRequest, request);

        return overrideRequest;
    }

    public static NetquallFinalCloseoutRequest convert(IntegrationContext ctx, ReceiptRequest request) {
        NetquallFinalCloseoutRequest finalCloseoutRequest  = new NetquallFinalCloseoutRequest();
        prepareGeneralState(finalCloseoutRequest, request);
        Booking booking = request.getBooking();
        Price receipt = request.getReceipt();
        NetquallBookingData bookingData = finalCloseoutRequest.getBookingData();
        bookingData.setJobType(BooleanUtils.isTrue(booking.isAsap()) ? NetquallJobType.ASAP : NetquallJobType.RESERVE);
        String vehicleType = AffiliateApiUtils.getReferenceValue(request.getVendorReference().getCustomerReference().getCompanyReferences(), "VEHICLE_TYPE");
        if  (StringUtils.isNotBlank(vehicleType)) {
            bookingData.setVehicleCode(NetquallVehicleCode.valueOf(vehicleType));
        }
        bookingData.setPickUpTime(booking.getDate());
        if (BooleanUtils.isTrue(booking.isAsDirected())) {
            bookingData.setServiceType(NetquallServiceType.HOURLY);
            bookingData.setHourlyDuration((int) Math.round(booking.getAsDirectedHours()));
        } else {
            bookingData.setServiceType(NetquallServiceType.TRANSFER);
        }
        bookingData.setNoOfPassengers(bookingData.getNoOfPassengers());
        bookingData.setRideType(NetquallRideType.BUSINESS);
        bookingData.setCurrencyCode(receipt.getCurrencyCode());

        //TODO
        bookingData.setDropOffTime(DateTime.now());
        bookingData.setOnLocationTime(DateTime.now());
        bookingData.setPassengerInCarTime(DateTime.now());
        //price
        bookingData.setRates(new NetquallRate());
        NetquallRate rate = bookingData.getRates();
        BigDecimal totalPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        //TODO
        rate.setBaseFare(receipt.getBaseFare());
        totalPrice = totalPrice.add(receipt.getBaseFare());

        bookingData.setGrandTotal(totalPrice);

        return finalCloseoutRequest;
    }

    public static Booking convert(IntegrationContext ctx, NetquallBookingRequest netquallBookingRequest) {
        NetquallBookingData bookingData = netquallBookingRequest.getBookingData();
        Booking booking = new Booking();
        switch (netquallBookingRequest.getHeader().getMethod()) {
            case RESERVE:
                if (Objects.equals(bookingData.getServiceType(), NetquallServiceType.HOURLY)) {
                    booking.setAsDirected(true);
                    booking.setAsDirectedHours(bookingData.getHourlyDuration() != null ? bookingData.getHourlyDuration().doubleValue() : 1);
                }
                booking.setPassengers(getActors(ctx, bookingData));
                booking.setContact(getContact(ctx, bookingData));
                booking.setPayment(getPayment(bookingData));
            case GET_QUOTE:
                if (Objects.equals(bookingData.getJobType(), NetquallJobType.ASAP))
                    booking.setAsap(true);
                else
                    booking.setDate(bookingData.getPickUpTime());
                booking.setProduct(getProduct(ctx, bookingData));
                if (Objects.equals(bookingData.getServiceType(), NetquallServiceType.HOURLY)) {
                    booking.setAsDirected(true);
                    booking.setAsDirectedHours(booking.getAsDirectedHours() != null ? booking.getAsDirectedHours() : 1);
                }
                List<Stop> stops = new ArrayList<>();
                booking.setStops(stops);
                stops.add(createStop(ctx, bookingData::getHoldOff, bookingData.getPickup()));
                if (CollectionUtils.isNotEmpty(bookingData.getStops())) {
                    for (NetquallAddress a : bookingData.getStops()) {
                        stops.add(createStop(ctx, () -> null, a));
                    }
                }
                if (bookingData.getDropOff() != null)
                    stops.add(createStop(ctx, () -> null, bookingData.getDropOff()));
                booking.setNumberOfPassengers(bookingData.getNoOfPassengers() != null ? bookingData.getNoOfPassengers() : 1);
                booking.setInstructions(getSpecialInstructions(ctx, bookingData));
                break;
        }

        return booking;
    }

    private static Payment getPayment(NetquallBookingData bookingData) {
        Payment payment = null;
        if (Objects.equals(bookingData.getPaymentMethod(), NetquallPaymentMethod.CREDIT_CARD)) {
            payment = new Payment();
            payment.setCreditCard(new CreditCard());
            CreditCard creditCard = payment.getCreditCard();
            creditCard.setPan(bookingData.getReservationCard().getCardNumber().replace("-", ""));
            creditCard.setHolderName(bookingData.getReservationCard().getCardHolderName());
            creditCard.setExpiryDate(new DateTime());
            DateTime dateTime = new YearMonth(bookingData.getReservationCard().getCardExpiryYear(), bookingData.getReservationCard().getCardExpiryMonth())
                    .toLocalDate(1)
                    .plusMonths(1)
                    .toDateTimeAtStartOfDay()
                    .minusMillis(1);
            creditCard.setExpiryDate(dateTime);

            creditCard.setBillingAddress(new BillingAddress());
            BillingAddress billingAddress = creditCard.getBillingAddress();
            billingAddress.setCity("London");
            billingAddress.setPostalCode("E14 4AD");
            billingAddress.setCountry("GB");
            billingAddress.setAddress1("20 Bank St");

            creditCard.setEmail("null@cybersource.com");
        }
        return payment;
    }

    private static String getSpecialInstructions(IntegrationContext ctx, NetquallBookingData bookingData) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(bookingData.getReservationNotes()))
            sb.append("NOTE::").append(bookingData.getReservationNotes()).append(":;");
        if (StringUtils.isNotBlank(bookingData.getChauffeurNotes()))
            sb.append("D::").append(bookingData.getChauffeurNotes()).append(":;");
        if (Objects.equals(bookingData.getShowToDriver(), NetquallShowToDriver.YES)) {
            NetquallPassenger passenger = null;
            if (CollectionUtils.isNotEmpty(bookingData.getPassengers()))
                passenger = bookingData.getPassengers().stream().filter(p -> StringUtils.isNotBlank(p.getMobilePhone())).findFirst().orElse(null);
            if (passenger != null)
                sb.append("D::").append("Passenger phone number: ").append(passenger.getMobilePhone()).append(":;");
        }
        return sb.length() == 0 ? null : sb.toString();
    }

    private static List<Actor> getActors(IntegrationContext ctx, NetquallBookingData bookingData) {
        List<Actor> actors = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bookingData.getPassengers())) {
            for (NetquallPassenger p : bookingData.getPassengers()) {
                Actor actor = new Actor();
                actor.setName(concatName(p));
                actor.setEmail(p.getPrimaryEmail());
                actor.setMobile(p.getMobilePhone());
                actors.add(actor);
            }
        }
        return actors;
    }

    private static Contact getContact(IntegrationContext ctx, NetquallBookingData bookingData) {
        Contact contact = null;
        if (bookingData.getBooker() != null
                && StringUtils.isNotBlank(bookingData.getBooker().getMobilePhone())
                && StringUtils.isNotBlank(bookingData.getBooker().getFirstName())) {
            contact = new Contact();
            contact.setName(concatName(bookingData.getBooker()));
            contact.setEmail(bookingData.getBooker().getPrimaryEmail());
            contact.setMobile(bookingData.getBooker().getMobilePhone());
        } else  if (CollectionUtils.isNotEmpty(bookingData.getPassengers())) {
            for (NetquallPassenger p : bookingData.getPassengers()) {
                if (BooleanUtils.isTrue(p.isLead())) {
                    contact = new Contact();
                    contact.setName(concatName(p));
                    contact.setEmail(p.getPrimaryEmail());
                    contact.setMobile(p.getMobilePhone());
                    break;
                }
            }
        }
        return contact;
    }

    private static String concatName(NetquallIndividual netquallIndividual) {
        String name = StringUtils.isBlank(netquallIndividual.getFirstName()) ? "" : netquallIndividual.getFirstName();
        if (StringUtils.isNotBlank(netquallIndividual.getLastName())) {
            name = name + " "  + netquallIndividual.getLastName();
        }
        return name;
    }

    private static Stop createStop(IntegrationContext ctx, Supplier<Integer> flightDelay, NetquallAddress address) {
        Stop stop = new Stop();
        switch (address.getAddressType()) {
            case AIRPORT:
                stop.setFlight(new Flight());
                stop.setType(Stop.Type.AIRPORT);
                stop.getFlight().setAirline(address.getAirLineCode());
                stop.getFlight().setNumber(address.getFlightNumber());
                stop.getFlight().setAirportCode(address.getAirportCode());
                stop.getFlight().setTerminalCode(address.getTerminal());
                stop.getFlight().setDelay(flightDelay.get());
                break;
            case ADDRESS:
            default:
                stop.setType(Stop.Type.ADDRESS);
                break;
        }
        stop.setAddressComponents(new AddressComponents());
        stop.setFormattedAddress(address.getFullAddress());
        stop.getAddressComponents().setCity(address.getCity());
        stop.getAddressComponents().setCountry(address.getCountry());
        stop.getAddressComponents().setPostalCode(address.getZip());
        stop.getAddressComponents().setStreetName(address.getStreet());
        stop.setLocation(new Location());
        stop.getLocation().setAccuracy(1.0);
        stop.getLocation().setLat(address.getLatitude());
        stop.getLocation().setLon(address.getLongitude());
        stop.setInstructions(address.getSpecialInstructions());

        return stop;
    }

    public NetquallBookingData convertPrice(NetquallBookingData bookingData, Price price) {
        BigDecimal zero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        bookingData.setRates(new NetquallRate());
        NetquallRate rates = bookingData.getRates();
        rates.setBaseFare(price.getBaseFare());
        BigDecimal airportFare = zero;
        BigDecimal parking = zero;
        BigDecimal waitTime = zero;
        BigDecimal tolls = zero;

        if (CollectionUtils.isNotEmpty(price.getAdjustments())) {
            for  (Adjustment a : price.getAdjustments()) {
                if (Objects.equals(a.getName(), "")) {

                }
            }
        }

        rates.setParking(parking);
        rates.setWaitTime(waitTime);
        rates.setTolls(tolls);
        rates.setAirportFee(airportFare);
        return bookingData;
    }

    public static NetquallQuoteResponse convertToEstimateRes(IntegrationContext ctx, EstimatesResponse estimatesResponse) {
        NetquallQuoteResponse response = new NetquallQuoteResponse();
        response.setQuoteReference(RequestContext.getRequestId());
        if (CollectionUtils.isNotEmpty(estimatesResponse.getEstimates())) {
            response.setRateData(new ArrayList<>());
            for (Estimate estimate : estimatesResponse.getEstimates()) {
                if (BooleanUtils.isTrue(estimate.isProductAvailable())) {
                    Price price = estimate.getFareEstimate();
                    NetquallRateData rateData = new  NetquallRateData();
                    response.getRateData().add(rateData);

                    rateData.setCurrencyCode(price.getCurrencyCode());
                    rateData.setServiceType(NetquallServiceType.TRANSFER);
                    rateData.setVehicleCode(getVehicleCode(ctx, estimate.getProduct()));

                    BigDecimal zero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal baseFare = price.getBaseFare() != null ? price.getBaseFare() : zero;
                    BigDecimal airportFare = zero;
                    BigDecimal parking = zero;
                    BigDecimal waitTime = price.getWaitingFare() != null ? price.getWaitingFare() : zero;
                    BigDecimal tolls = zero;
                    BigDecimal discount = zero;
                    BigDecimal serviceCharge = zero;
                    BigDecimal suggestedGratuity = zero;
                    if (CollectionUtils.isNotEmpty(price.getAdjustments())) {
                        for (Adjustment adjustment : price.getAdjustments()) {
                            if (adjustment.getAmount() != null) {
                                if (Objects.equals(adjustment.getName(), "PARKING_FEE")) {
                                    parking = parking.add(adjustment.getAmount());
                                } else if (Objects.equals(adjustment.getName(), "TOLLS")) {
                                    tolls = tolls.add(adjustment.getAmount());
                                } else if (Objects.equals(adjustment.getName(), "DISCOUNT")) {
                                    discount = discount.add(adjustment.getAmount());
                                } else if (Objects.equals(adjustment.getName(), "GRATUITY")) {
                                    suggestedGratuity = suggestedGratuity.add(adjustment.getAmount());
                                } else if (Objects.equals(adjustment.getName(), "SERVICE_CHARGE")) {
                                    serviceCharge = serviceCharge.add(adjustment.getAmount());
                                } else {
                                    baseFare = baseFare.add(adjustment.getAmount());
                                }
                            }
                        }
                    }

                    rateData.setGrandTotal(price.getTotalCharged());
                    rateData.setFareBreakdown(new NetquallRate());
                    NetquallRate rate = rateData.getFareBreakdown();
                    rate.setBaseFare(baseFare);
                    rate.setTolls(tolls);
                    rate.setAirportFee(airportFare);
                    rate.setParking(parking);
                    rate.setWaitTime(waitTime);
                    rate.setServiceCharge(serviceCharge);
                    rate.setSuggestedGratuity(suggestedGratuity);
                    rate.setDiscount(discount);
                    rateData.setGrandTotal(rate.getBaseFare().add(rate.getTolls()).add(rate.getAirportFee())
                            .add(rate.getParking()).add(rate.getWaitTime()).add(rate.getServiceCharge()).add(rate.getSuggestedGratuity())
                            .add(rate.getDiscount())
                    );

                    rateData.setGrandTotal(baseFare.add(airportFare).add(parking).add(waitTime).add(tolls));
                }
            }
        }

        return response;
    }

    public static NetquallResponse convertToVendorRes(IntegrationContext ctx, Response response) {
        NetquallResponse netquallResponse = new NetquallResponse();
        netquallResponse.setMessage(response.getError().getMessage());
        netquallResponse.setStatus(NetquallStatus.SUCCESS);
        return  netquallResponse;
    }

    public static NetquallCancelResponse convertToVendorRes(IntegrationContext ctx, CancelBookingResponse bookingResponse) {
        return createRefRes(ctx, bookingResponse.getError(), bookingResponse.getVendorReference(), bookingResponse.getBookingReference(), NetquallCancelResponse.class);
    }

    public static NetquallBookingResponse convertToVendorRes(IntegrationContext ctx, NetquallHeaderMethod headerMethod, BookingResponse bookingResponse) {
        NetquallBookingResponse response;
        if (Objects.equals(headerMethod, NetquallHeaderMethod.PROVIDER_RESERVATION_UPDATE)) {
            response = new NetquallBookingResponse();
            response.setStatus(NetquallStatus.SUCCESS);
        } else {
            response = createRefRes(ctx, bookingResponse.getError(), bookingResponse.getVendorReference(), bookingResponse.getBookingReference(), NetquallBookingResponse.class);
            if (Objects.equals(response.getStatus(), NetquallStatus.SUCCESS))
                response.setAccepted(AcceptedStatus.ACCEPTED);
            else
                response.setAccepted(AcceptedStatus.REJECTED);
        }
        return  response;
    }

    private static  <T extends NetquallRefResponse> T createRefRes(IntegrationContext ctx,
                                                                   com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.Error error,
                                                                   VendorReference vendorReference, BookingReference bookingReference,
                                                                   Class<T> refResponseClass) {
        T v;
        try {
            v = refResponseClass.getConstructor().newInstance();
        } catch (Throwable e) {
            throw new ServiceException(ErrorCode.SERVER_ERROR, "Failed to make response", e);
        }
        if (Objects.equals(error.getCode(), 0)) {
            v.setSenderBookingNumber(vendorReference.getNumber());
            v.setReceiverBookingNumber(bookingReference.getId());
            v.setExternalReference(bookingReference.getNumber());
            v.setStatus(NetquallStatus.SUCCESS);
            v.setMessage("Success");
        } else {
            v.setSenderBookingNumber(vendorReference.getNumber());
            //v.setReceiverBookingNumber(bookingReference.q());
            v.setStatus(NetquallStatus.ERROR);
            v.setMessage(error.getMessage());
        }
        return v;
    }

    private static String getProduct(IntegrationContext ctx, NetquallBookingData bookingData) {
        BiMap<String, String> vehicleTypeMap = ctx.getIntegrationSettings().getVehicleTypeMap();
        String product;
        if (vehicleTypeMap != null && bookingData.getVehicleCode() != null && !Objects.equals(bookingData.getVehicleCode(), NetquallVehicleCode.UNKNOWN)
                && (product = vehicleTypeMap.get(bookingData.getVehicleCode().getCode())) != null
        ) {
            return product;
        } else {
            throw new ServiceException(ErrorCode.NOT_FOUND, "Vehicle Code isn't mapped");
        }

    }

    private static NetquallVehicleCode getVehicleCode(IntegrationContext ctx, String product) {
        BiMap<String, String> vehicleTypeMap = ctx.getIntegrationSettings().getVehicleTypeMap();
        if (vehicleTypeMap != null) {
            return NetquallVehicleCode.parseCode(vehicleTypeMap.inverse().get(product));
        } else {
            throw new ServiceException(ErrorCode.NOT_FOUND, "Vehicle Code isn't mapped");
        }
    }
}
