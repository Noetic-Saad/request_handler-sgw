package com.noeticworld.sgw.subscriber.dto;

public class LoginRequestDto {

    private String msisdn;
    private String trackerId;
    private Integer otpNumber;
    private boolean otp;
    private String sessionId;
    private String remoteServerIp;
    private String localServerIp;

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
