/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.mappers;

import com.haulmont.bali.lang.AffiliateExceptionUtils;
import com.haulmont.monaco.ServiceException;
import com.haulmont.monaco.rs.jersey.mappers.DefaultServiceExceptionMapper;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.Error;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.dto.ErrorCode;
import com.haulmont.shamrock.affiliate.integrations.core.affiliates_api.exceptions.AffiliatesApiException;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallQuoteResponse;
import com.haulmont.shamrock.affiliate.netquall.dto.resp.NetquallResponse;
import org.apache.http.conn.HttpHostConnectException;

import javax.management.ServiceNotFoundException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

public class NetquallApiServiceExceptionMapper extends DefaultServiceExceptionMapper {

    @Override
    protected Object getEntity(ServiceException ex) {
        NetquallResponse resource;
        AffiliatesApiException apiException;
        NotAuthorizedException notAuthorizedException;
        if ((apiException = AffiliateExceptionUtils.getCastedCause(ex, AffiliatesApiException.class)) != null) {
            resource = new NetquallResponse(apiException.getMessage());
        } else if ((notAuthorizedException = AffiliateExceptionUtils.getCastedCause(ex, NotAuthorizedException.class)) != null) {
            resource = new NetquallResponse(notAuthorizedException.getMessage());
        } else {
            resource = new NetquallResponse(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return resource;
    }

    private Response.Status getHttpStatus(ServiceException ex) {
        AffiliatesApiException apiException;
        ServiceNotFoundException serviceNotFoundException;
        if ( (apiException = AffiliateExceptionUtils.getCastedCause(ex, AffiliatesApiException.class)) != null ) {
            Error error = apiException.getError();
            Integer code = error.getCode();
            ErrorCode errorCode = ErrorCode.fromValue(code);
            Response.Status status;
            switch (errorCode) {
                case OK:
                    status = Response.Status.OK;
                    break;
                case FAILED_TO_AUTHORISATION:
                    status = Response.Status.UNAUTHORIZED;
                    break;
                case JOB_NOT_FOUND:
                    status = Response.Status.NOT_FOUND;
                    break;
                case UNSUPPORTED_OPERATION:
                    status = Response.Status.SERVICE_UNAVAILABLE;
                    break;
                default:
                    status = Response.Status.INTERNAL_SERVER_ERROR;
                    break;
            }
            return status;
        }
        Response.Status status = Response.Status.fromStatusCode(super.getHttpStatusCode(ex));
        return status == null ? Response.Status.INTERNAL_SERVER_ERROR : status;
    }

    @Override
    protected int getHttpStatusCode(ServiceException ex) {
        return getHttpStatus(ex).getStatusCode();
    }
}
