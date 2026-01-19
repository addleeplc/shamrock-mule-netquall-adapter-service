/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto.req;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.haulmont.bali.jackson.joda.NetquallRequestDeserializer;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallCredentials;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallHeader;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonDeserialize(using = NetquallRequestDeserializer.class)
public class NetquallRequest extends AbstractJsonEntity {
    @JsonProperty("Header")
    private NetquallHeader header;
    @JsonProperty("Credentials")
    private NetquallCredentials credentials;

    public NetquallHeader getHeader() {
        return header;
    }

    public void setHeader(NetquallHeader header) {
        this.header = header;
    }

    public NetquallCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(NetquallCredentials credentials) {
        this.credentials = credentials;
    }
}
