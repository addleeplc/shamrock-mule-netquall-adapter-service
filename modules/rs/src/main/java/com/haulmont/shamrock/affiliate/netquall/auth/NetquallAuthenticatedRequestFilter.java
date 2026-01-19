/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.auth;

import com.haulmont.shamrock.affiliate.integrations.core.dto.AuthCredentials;
import com.haulmont.shamrock.affiliate.integrations.core.model.Affiliate;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.AuthenticatedRequestFilter;

public class NetquallAuthenticatedRequestFilter extends AuthenticatedRequestFilter {

    @Override
    protected Affiliate getAffiliate(AuthCredentials authCredentials) {
        return super.getAffiliate(authCredentials);
    }
}
