/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.monaco.jackson.annotations.SensitiveData;
import com.haulmont.monaco.jackson.maskers.AuthTokenMasker;
import com.haulmont.monaco.jackson.maskers.DefaultMasker;
import com.haulmont.monaco.jackson.maskers.NameMasker;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallCredentials extends AbstractJsonEntity {
    @JsonProperty("AuthId")
    @SensitiveData(masker = NameMasker.class)
    private String authId;
    @JsonProperty("AuthPassword")
    @SensitiveData()
    private String authPassword;

    public NetquallCredentials() {
    }

    public NetquallCredentials(String authId, String authPassword) {
        this.authId = authId;
        this.authPassword = authPassword;
    }

    public String getAuthId() {
        return authId;
    }
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }


}
