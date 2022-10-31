package com.noeticworld.sgw.subscriber.dto;

public class RequestDto {

    private String msisdn;
    private String trackerId;
    private Integer otpNumber;
    private boolean otp;

    public boolean isOtp() {
        return otp;
    }

    public void setOtp(boolean otp) {
        this.otp = otp;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }

    public Integer getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(Integer otpNumber) {
        this.otpNumber = otpNumber;
    }
}
