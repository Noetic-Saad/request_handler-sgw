package com.noeticworld.sgw.subscriber.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class CustomMessage implements Serializable {

    private String text;
    private String msisdn;
    private String action;
    private String dateTime;
    private String corelationId;
    private String vendorPlanId;
    private String trackerId;
    private boolean otp;
    private Integer otpNumber;
    private String sessionId;
    private String remoteServerIp;
    private String localServerIp;

//    public CustomMessage(@JsonProperty("text") String text,
//                         @JsonProperty("msisdn") String msisdn,
//                         @JsonProperty("action") String action,
//                         @JsonProperty("dateTime") LocalDateTime localDateTime,
//                         @JsonProperty("corelationId") String corelationId,
//                         @JsonProperty("vendorPlanId") String vendorPlanId) {
//        this.text = text;
//        this.msisdn = msisdn;
//        this.action = action;
//        this.dateTime = localDateTime;
//        this.corelationId = corelationId;
//        this.vendorPlanId = vendorPlanId;
//    }

    @Override
    public String toString() {
        return String.format("Text: %s, MSISDN: %s, Action: %s, DateTime: %s, CorelationId: %s, VendorPlan: %s",
                text, msisdn, dateTime, action, corelationId, vendorPlanId);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCorelationId() {
        return corelationId;
    }

    public void setCorelationId(String corelationId) {
        this.corelationId = corelationId;
    }

    public String getVendorPlanId() {
        return vendorPlanId;
    }

    public void setVendorPlanId(String vendorPlanId) {
        this.vendorPlanId = vendorPlanId;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }

    public boolean isOtp() {
        return otp;
    }

    public void setOtp(boolean otp) {
        this.otp = otp;
    }

    public Integer getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(Integer otpNumber) {
        this.otpNumber = otpNumber;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRemoteServerIp() {
        return remoteServerIp;
    }

    public void setRemoteServerIp(String remoteServerIp) {
        this.remoteServerIp = remoteServerIp;
    }

    public String getLocalServerIp() {
        return localServerIp;
    }

    public void setLocalServerIp(String localServerIp) {
        this.localServerIp = localServerIp;
    }
}
