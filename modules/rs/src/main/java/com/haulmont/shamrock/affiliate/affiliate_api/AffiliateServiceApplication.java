/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.affiliate_api;

import com.haulmont.shamrock.affiliate.affiliate_api.resources.AffiliateIntegratorApiResource;
import com.haulmont.shamrock.affiliate.integrations.rs.affiliates_api.AbstractAffiliatesApiResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/affiliate/api/")
public class AffiliateServiceApplication extends AbstractAffiliatesApiResourceConfig {
    public AffiliateServiceApplication() {
        register(AffiliateIntegratorApiResource.class);
    }

}
