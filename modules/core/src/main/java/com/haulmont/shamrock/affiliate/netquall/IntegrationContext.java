/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.haulmont.shamrock.affiliate.integrations.core.model.Affiliate;
import com.haulmont.shamrock.affiliate.integrations.core.services.dto.affiliates_registry.NetquallApiAdapterSettings;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeaderMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class IntegrationContext extends com.haulmont.shamrock.affiliate.integrations.core.IntegrationContext {
    public IntegrationContext(Affiliate affiliate) {
        super(affiliate);
    }

    public IntegrationContext(com.haulmont.shamrock.affiliate.integrations.core.IntegrationContext integrationContext) {
        this(integrationContext.getAffiliate());
    }

    public NetquallApiAdapterSettings getIntegrationSettings() {
        return super.getIntegrationSettings(NetquallApiAdapterSettings.class);
    }

    @Override
    protected Object get(Object v, Class<?> returnType) {
        if (Map.class.isAssignableFrom(returnType)) {
            Map<Object, Object> map = null;
            if (v instanceof String) {
                String value = (String) v;
                String[] vv = value.split(";");
                map = returnType.isAssignableFrom(BiMap.class) ? HashBiMap.create() : new HashMap<>();
                for (String s : vv) {
                    String[] ss = s.split(":");
                    try {
                        map.put(ss[0], ss[1]);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return map;
        } else {
            return super.get(v, returnType);
        }
    }

    public static Method getEnumJsonCreatorMethod(Class<?> enumClass) {
        if (!enumClass.isEnum()) {
            return null;
        }

        for (Method method : enumClass.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) &&
                    method.isAnnotationPresent(JsonCreator.class)) {
                return method;
            }
        }
        return null; // не найден
    }

}
