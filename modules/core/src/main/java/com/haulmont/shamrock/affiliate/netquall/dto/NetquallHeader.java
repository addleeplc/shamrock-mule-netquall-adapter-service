/*
 * Copyright 2008 - 2025 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.shamrock.affiliate.netquall.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haulmont.shamrock.affiliate.integrations.core.dto.AbstractJsonEntity;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetquallHeader extends AbstractJsonEntity {
    @JsonProperty("Method")
    private NetquallHeaderMethod method;
    @JsonProperty("ChannelType")
    private NetquallChannelType channelType;
    @JsonProperty("SenderCode")
    private String senderCode;
    @JsonProperty("ReceiverCode")
    private String receiverCode;
    @JsonProperty("BookingTxnRef")
    private String bookingTxnRef;
    @JsonProperty("ReceiverBookingNumber")
    private String receiverBookingNumber;
    @JsonProperty("SenderBookingNumber")
    private String senderBookingNumber;
    @JsonProperty("AccountIdentifier")
    private String accountIdentifier;

    public NetquallHeaderMethod getMethod() {
        return method;
    }

    public void setMethod(NetquallHeaderMethod method) {
        this.method = method;
    }

    public NetquallChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(NetquallChannelType channelType) {
        this.channelType = channelType;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getBookingTxnRef() {
        return bookingTxnRef;
    }

    public void setBookingTxnRef(String bookingTxnRef) {
        this.bookingTxnRef = bookingTxnRef;
    }

    public String getReceiverBookingNumber() {
        return receiverBookingNumber;
    }

    public void setReceiverBookingNumber(String receiverBookingNumber) {
        this.receiverBookingNumber = receiverBookingNumber;
    }

    public String getSenderBookingNumber() {
        return senderBookingNumber;
    }

    public void setSenderBookingNumber(String senderBookingNumber) {
        this.senderBookingNumber = senderBookingNumber;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }
}
