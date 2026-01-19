/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.auth;

import com.haulmont.shamrock.affiliate.integrations.core.utils.ReflectionUtils;
import com.haulmont.shamrock.affiliate.integrations.rs.auth.Authenticated;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.lang.reflect.Method;

public class NetquallAuthenticatedBinding implements DynamicFeature {
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        Class<?> resourceClass = resourceInfo.getResourceClass();
        if (ReflectionUtils.isAnnotationPresent(resourceClass, Authenticated.class)) {
            context.register(NetquallAuthenticatedRequestFilter.class, Priorities.AUTHENTICATION);
        } else {
            Method resourceMethod = resourceInfo.getResourceMethod();
            if (resourceMethod.isAnnotationPresent(Authenticated.class)) {
                context.register(NetquallAuthenticatedRequestFilter.class, Priorities.AUTHENTICATION);
            }
        }
    }
}
