/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.bali.jackson.joda;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeaderMethod;
import com.haulmont.shamrock.affiliate.netquall.dto.req.*;

import java.io.IOException;

public class NetquallRequestDeserializer extends JsonDeserializer<NetquallRequest>{

    @Override
    public NetquallRequest deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        JsonNode root = p.readValueAsTree();//mapper.readTree(p);

        JsonNode methodNode = root
                .path("Header")
                .path("Method");

        if (methodNode.isMissingNode()) {
            throw new IllegalArgumentException("Header.Method is missing");
        }
        String methodTxt = methodNode.asText();
        NetquallHeaderMethod method = NetquallHeaderMethod.parseCode(methodTxt);

        Class<? extends NetquallRequest> targetClass;
        switch (method) {
            case RESERVE:
            case PROVIDER_RESERVATION_UPDATE:
                targetClass = NetquallBookingRequest.class;
                break;
            case CANCEL_RESERVATION:
            case PROVIDER_CANCEL:
                targetClass = NetquallProviderCancelRequest.class;
                break;
            case GET_QUOTE:
                targetClass = NetquallQuoteRequest.class;
                break;
            case AUDIT_RESPONSE:
                targetClass = NetquallAuditRequest.class;
                break;
            case PAYMENT_STATUS_CALLBACK:
                targetClass = NetquallPaymentStatusCallback.class;
                break;
            case STATUS_UPDATES_CALLBACK:
                targetClass = NetquallStatusUpdatesCallback.class;
                break;
            default:
                throw new IllegalArgumentException("Unknown Header.Method: " + methodTxt);
        }
        return mapper.treeToValue(root, targetClass);
        //return mapper.readValue(root.traverse(p.getCodec()), targetClass);
        //mapper.reader()
        //return mapper.readerFor(targetClass).readValue(root);
    }
}