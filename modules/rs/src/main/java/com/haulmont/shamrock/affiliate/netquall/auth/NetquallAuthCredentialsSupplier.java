/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.auth;

import com.haulmont.bali.lang.JavaWsRsUtils;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.Error;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.ErrorCode;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.exceptions.AffiliatesApiException;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.AuthCredentialsSupplier;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallCredentials;
import com.haulmont.shamrock.affiliate.netquall.dto.req.NetquallRequest;

import javax.ws.rs.container.ContainerRequestContext;
import java.util.Base64;

public class NetquallAuthCredentialsSupplier implements AuthCredentialsSupplier {
    @Override
    public String get(ContainerRequestContext requestContext, String name) {
        try {
            //String body = IOUtils.toString(requestContext.getEntityStream(), StandardCharsets.UTF_8);
            //byte[] byteArray = IOUtils.toByteArray(requestContext.getEntityStream());
            //String body = new String(byteArray, StandardCharsets.UTF_8);
            //requestContext.setEntityStream(new ByteArrayInputStream(byteArray));
            //InputStream in = IOUtils.toInputStream(body, StandardCharsets.UTF_8);
            //NetquallRequest netquallRequest = JsonSerializer.getInstance().readValue(body, NetquallRequest.class);

            NetquallRequest netquallRequest = JavaWsRsUtils.readRequest(requestContext, NetquallRequest.class);
            NetquallCredentials credentials = netquallRequest.getCredentials();
            return Base64.getEncoder().encodeToString(String.format("%s:%s", credentials.getAuthId(), credentials.getAuthPassword()).getBytes());
            //return Base64.getEncoder().encodeToString(String.format("%s:%s", "netquall", "netquall_in").getBytes());
        } catch (Throwable e) {
            throw new AffiliatesApiException(new Error(ErrorCode.FAILED_TO_AUTHORISATION));
            //throw new ServiceException(ErrorCode.UNAUTHORIZED, "Unauthorized", e);
        }
    }
}
