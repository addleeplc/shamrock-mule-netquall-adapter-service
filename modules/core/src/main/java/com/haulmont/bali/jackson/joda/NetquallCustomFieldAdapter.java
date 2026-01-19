/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.bali.jackson.joda;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.haulmont.bali.lang.CallUtils;
import com.haulmont.shamrock.affiliate.netquall.dto.NetquallCustomField;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NetquallCustomFieldAdapter extends DateTimeAdapter {

    public static class Deserializer extends JsonDeserializer<List<NetquallCustomField>> {
        @Override
        public List<NetquallCustomField> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            JsonNode node = p.getCodec().readTree(p);
//            if (!node.isObject()) {
//                throw ctxt.mappingException("Expected JSON object for CustomFields");
//            }

            List<NetquallCustomField> fields = new ArrayList<>();
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String name = fieldNames.next();
                String value = node.get(name).asText();
                fields.add(new NetquallCustomField(name, value));
            }
            return fields;
        }
    }

    public static class Serializer extends JsonSerializer<List<NetquallCustomField>> {

        @Override
        public void serialize(List<NetquallCustomField> fields, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject(); // start object: {
            for (NetquallCustomField field : fields) {
                gen.writeStringField(field.getKey(), field.getValue());
            }
            gen.writeEndObject(); // end object: }
        }
    }
}
