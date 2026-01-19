/*
 * Copyright (c) 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall;

import com.haulmont.monaco.rs.jersey.Application;
import com.haulmont.shamrock.affiliate.integrations.rs.AbstractIntegrationResourceConfig;
import com.haulmont.shamrock.affiliate.netquall.auth.NetquallAuthenticatedBinding;
import com.haulmont.shamrock.affiliate.netquall.mappers.NetquallApiExceptionMapper;
import com.haulmont.shamrock.affiliate.netquall.mappers.NetquallApiServiceExceptionMapper;
import com.haulmont.shamrock.affiliate.netquall.resources.NetquallIntegratorApiResource;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/netquall/api/")
public class NetquallServiceApplication extends AbstractIntegrationResourceConfig {
    public NetquallServiceApplication() {
        super();
        register(NetquallIntegratorApiResource.class);
    }

    @Override
    protected void registerExceptionMappers() {
        register(NetquallApiExceptionMapper.class);
        register(NetquallApiServiceExceptionMapper.class);
    }

    @Override
    protected void registerAuthenticatedBinding() {
        register(NetquallAuthenticatedBinding.class);
    }
}
