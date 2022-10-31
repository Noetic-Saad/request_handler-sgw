package com.noeticworld.sgw.subscriber.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public final class RequestStatus implements Serializable {

    private String correlationid;
    private String resultStatus;
    private Boolean isFetched;
    private Timestamp cdatetime;
    private String description;
    private Long vendorPlanId;

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Boolean getFetched() {
        return isFetched;
    }

    public void setFetched(Boolean fetched) {
        isFetched = fetched;
    }

    public Timestamp getCdatetime() {
        return cdatetime;
    }

    public void setCdatetime(Timestamp cdatetime) {
        this.cdatetime = cdatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVendorPlanId() {
        return vendorPlanId;
    }

    public void setVendorPlanId(Long vendorPlanId) {
        this.vendorPlanId = vendorPlanId;
    }



    @Override
    public String toString() {
        return "RequestStatus{" +
                "correlationid='" + correlationid + '\'' +
                ", resultStatus='" + resultStatus + '\'' +
                ", isFetched=" + isFetched +
                ", cdatetime=" + cdatetime +
                ", description='" + description + '\'' +
                ", vendorPlanId=" + vendorPlanId +
                '}';
    }
}
