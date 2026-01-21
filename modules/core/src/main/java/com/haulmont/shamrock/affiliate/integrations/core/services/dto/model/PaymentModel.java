/*
 * Copyright 2008 - 2026 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.integrations.core.services.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class PaymentModel {

    @JsonProperty("type")
    private Type type;

    @JsonProperty("supplement_payment_model")
    private SupplementModel supplementPaymentModel;

    //


    public PaymentModel() {
        super();
    }

    private PaymentModel(Type type) {
        this(type, null);
    }

    private PaymentModel(Type type, SupplementModel supplementPaymentModel) {
        this.type = type;
        this.supplementPaymentModel = supplementPaymentModel;
    }

    //

    public static PaymentModel of(Type type) {
        return new PaymentModel(type);
    }

    public static PaymentModel of(Type type, Type supplementPaymentModel) {
        if (supplementPaymentModel == null) {
            return new PaymentModel(type);
        }
        return new PaymentModel(type, new SupplementModel(supplementPaymentModel));
    }

    //

    public Type getType() {
        return type;
    }

    public SupplementModel getSupplementPaymentModel() {
        return supplementPaymentModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PaymentModel that = (PaymentModel) o;
        return type == that.type && Objects.equals(supplementPaymentModel, that.supplementPaymentModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, supplementPaymentModel);
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "type=" + type +
                ", supplementPaymentModel=" + supplementPaymentModel +
                '}';
    }

    //

    public enum Type {
        INVOICE("invoice"),
        CASH("cash"),
        CREDIT_CARD("credit_card");

        private final String code;

        Type(String code) {
            this.code = code;
        }

        @JsonValue
        public String getCode() {
            return code;
        }
    }

    public static class SupplementModel {

        @JsonProperty("type")
        private Type type;

        public SupplementModel(Type type) {
            this.type = type;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            SupplementModel that = (SupplementModel) o;
            return type == that.type;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(type);
        }

        @Override
        public String toString() {
            return "{" + type + '}';
        }
    }
}
