/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.affiliates_registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.BiMap;
import com.haulmont.shamrock.affiliate.integrations.core.model.Credentials;

public interface NetquallApiAdapterSettings extends AffiliatesApiAdapterSettings {
    String NETQUALL_API_CREDENTIALS = "netquall_api_credentials";

    @JsonProperty(NETQUALL_API_CREDENTIALS)
    Credentials.Basic getNetquallCredentials();

//    @JsonProperty("sixt_auth_url")
//    String getAuthURL();

    @JsonProperty("vehicle_type_map")
    BiMap<String, String> getVehicleTypeMap();

    @JsonProperty("account_map")
    BiMap<String, String> getAccountMap();

    @JsonProperty("netquall_vendor_code")
    String getNetquallVendorCode();

    @JsonProperty("netquall_booker_code")
    String getNetquallBookerCode();



}
