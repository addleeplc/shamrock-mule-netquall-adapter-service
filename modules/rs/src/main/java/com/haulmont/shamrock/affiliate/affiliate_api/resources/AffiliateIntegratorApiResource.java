/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.affiliate_api.resources;

import com.haulmont.monaco.AppContext;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.affiliates_registry.AffiliatesApiAdapterSettings;
import com.haulmont.shamrock.affiliate.integrations.rs.affiliates_api.AbstractAffiliatesApiResource;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.AuthCredentialsSupplier;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.Authenticated;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.Credentials;
import com.haulmont.shamrock.affiliate.netquall.services.NetquallOutboundIntegratorAdapterService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/2.0")
@Authenticated(credentials = @Credentials(supplier = AuthCredentialsSupplier.Basic.class), name = AffiliatesApiAdapterSettings.AFFILIATE_CREDENTIALS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AffiliateIntegratorApiResource extends AbstractAffiliatesApiResource {
    public AffiliateIntegratorApiResource() {
        super(() -> AppContext.getBean(NetquallOutboundIntegratorAdapterService.class));
    }


}
