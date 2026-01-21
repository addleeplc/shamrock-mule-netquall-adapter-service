
/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.haulmont.bali.jackson.joda.DateTimeAdapter;
import com.haulmont.bali.jackson.joda.DurationAdapter;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.HashMap;
import java.util.Map;

public class Stop extends AbstractJsonEntity {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private Type type;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("tag")
    private String tag;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    @JsonProperty("address_components")
    private AddressComponents addressComponents;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("collection_delay")
    @JsonDeserialize(using = DurationAdapter.Deserializer.class)
    @JsonSerialize(using = DurationAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private Period collectionDelay;

    @JsonProperty("arrival_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime arrivalDate;

    @JsonProperty("completion_date")
    @JsonDeserialize(using = DateTimeAdapter.Deserializer.class)
    @JsonSerialize(using = DateTimeAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime completionDate;

    @JsonProperty("waiting_duration")
    @JsonDeserialize(using = DurationAdapter.Deserializer.class)
    @JsonSerialize(using = DurationAdapter.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private Period waitingDuration;

    //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public AddressComponents getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(AddressComponents addressComponents) {
        this.addressComponents = addressComponents;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Period getCollectionDelay() {
        return collectionDelay;
    }

    public void setCollectionDelay(Period collectionDelay) {
        this.collectionDelay = collectionDelay;
    }

    public DateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(DateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public DateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(DateTime completionDate) {
        this.completionDate = completionDate;
    }

    public Period getWaitingDuration() {
        return waitingDuration;
    }

    public void setWaitingDuration(Period waitingDuration) {
        this.waitingDuration = waitingDuration;
    }

    //

    public enum Type {

        ADDRESS("ADDRESS"),
        AIRPORT("AIRPORT"),
        TRAIN_STATION("TRAIN_STATION");
        private final String value;
        private static final Map<String, Type> constants = new HashMap<String, Type>();

        static {
            for (Type c : values()) {
                constants.put(c.value, c);
            }
        }

        Type(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Type fromValue(String value) {
            Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
