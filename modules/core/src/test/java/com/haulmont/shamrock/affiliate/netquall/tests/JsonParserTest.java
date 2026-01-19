/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.tests;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.haulmont.bali.jackson.JsonSerializer;
import com.haulmont.bali.jackson.joda.NetquallRequestDeserializer;
import com.haulmont.shamrock.affiliate.netquall.dto.req.NetquallRequest;

public class JsonParserTest {
    public static void main(String[] args) {
        String msg = "{\n" +
                "  \"Header\": {\n" +
                "    \"ChannelType\": \"CorporateNetwork\",\n" +
                "    \"SenderCode\": \"MSC\",\n" +
                "    \"SenderBookingNumber\": \"196447\",\n" +
                "    \"ReceiverBookingNumber\": \"\",\n" +
                "    \"ReceiverCode\": \"Ibpag\",\n" +
                "    \"Method\": \"Reserve\",\n" +
                "    \"AccountIdentifier\": \"\",\n" +
                "    \"BookingTxnRef\": \"\"\n" +
                "  }\n" +
                "}";

//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(NetquallRequest.class, new NetquallRequestDeserializer());
//        JsonSerializer.getInstance().getObjectMapper().registerModule(module);

        NetquallRequest request = JsonSerializer.getInstance().readValue(msg, NetquallRequest.class);
    }
}
