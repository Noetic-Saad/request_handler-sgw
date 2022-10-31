package com.noeticworld.sgw.subscriber.dto;

public class CustomMoMessage {

    private String msisdn;
    private Integer operatorId;
    private String shortCode;
    private Integer connectivityPointId;
    private String correlationId;
    private Integer smsId;
    private String smsText;



    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getShortcode() {
        return shortCode;
    }

    public void setShortcode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Integer getConnectivityPointId() {
        return connectivityPointId;
    }

    public void setConnectivityPointId(Integer connectivityPointId) {
        this.connectivityPointId = connectivityPointId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }
}