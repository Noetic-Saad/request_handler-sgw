package com.noeticworld.sgw.subscriber.dto;

import java.io.Serializable;

public class CustomDcbMessage implements Serializable {

    private String msisdn;
    private String transId;
    private float amount;
    private String serviceId;
    private String remoteIpAddrres;
    private String vendorPlanId;
    private String correlationId;
    private String dateTime;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRemoteIpAddrres() {
        return remoteIpAddrres;
    }

    public void setRemoteIpAddrres(String remoteIpAddrres) {
        this.remoteIpAddrres = remoteIpAddrres;
    }

    public String getVendorPlanId() {
        return vendorPlanId;
    }

    public void setVendorPlanId(String vendorPlanId) {
        this.vendorPlanId = vendorPlanId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "CustomDcbMessage{" +
                "msisdn='" + msisdn + '\'' +
                ", transId='" + transId + '\'' +
                ", amount=" + amount +
                ", serviceId='" + serviceId + '\'' +
                ", remoteIpAddrres='" + remoteIpAddrres + '\'' +
                ", vendorPlanId='" + vendorPlanId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
