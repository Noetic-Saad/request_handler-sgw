package com.noeticworld.sgw.subscriber.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class CustomMoMessage_ implements Serializable {

    private Integer sequenceNumber;
    private String msisdn;
    private String shortcode;
    private String smsText;
    private String subject;
    private Integer messageType;
    private Integer connectivityPointId;
    private Integer priceGroup;
    private Integer protocolType;
    private Integer mobileOperatorId;
    private String serviceQueueName;
    private Boolean requestDeliveryReport;
    private Integer smsId;
    private Integer noOfRetries;
    private String connectionPointName;
    private String serviceName;
    private Integer routeUid;
    private Integer mmsObjectId;
    private String telcoCode;
    private Integer serviceId;
    private Integer priority;
    private Integer status;
    private String refNo;
    private Date expiryTime;
    private static final long serialVersionUID = 1L;
    private String correlationId;
    private HashMap<ExtraParameterKey, String> extraParameters = new HashMap();

    public static enum ExtraParameterKey {
        SEND_MT_ONLY,
        LOG_ACTION,
        EXPIRY_DATE,
        FLAG,
        ACTION,
        IS_RETRY;

        public static final String ACTION_VALUE_ACTIVATION = "1";
        public static final String ACTION_VALUE_DEACTIVATION = "2";
        public static final String ACTION_VALUE_AUTOEXPIRY = "3";
        public static final String FLAG_VALUE_SUCCESS = "1";
        public static final String FLAG_VALUE_FAILURE = "2";
        public static final String IS_RETRY_VALUE_TRUE = "true";
        public static final String LOG_ACTION_VALUE_LOG_ONLY = "1";
        public static final String LOG_ACTION_VALUE_LOG_ON_SUCCESSFUL_SEND = "2";
        public static final String SEND_MT_ONLY_VALUE_TRUE = "true";

        private ExtraParameterKey() {
        }
    }

    public HashMap<ExtraParameterKey, String> getExtraParameters() {
        return this.extraParameters;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getConnectivityPointId() {
        return connectivityPointId;
    }

    public void setConnectivityPointId(Integer connectivityPointId) {
        this.connectivityPointId = connectivityPointId;
    }

    public Integer getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(Integer priceGroup) {
        this.priceGroup = priceGroup;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public Integer getMobileOperatorId() {
        return mobileOperatorId;
    }

    public void setMobileOperatorId(Integer mobileOperatorId) {
        this.mobileOperatorId = mobileOperatorId;
    }

    public String getServiceQueueName() {
        return serviceQueueName;
    }

    public void setServiceQueueName(String serviceQueueName) {
        this.serviceQueueName = serviceQueueName;
    }

    public Boolean getRequestDeliveryReport() {
        return requestDeliveryReport;
    }

    public void setRequestDeliveryReport(Boolean requestDeliveryReport) {
        this.requestDeliveryReport = requestDeliveryReport;
    }

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public Integer getNoOfRetries() {
        return noOfRetries;
    }

    public void setNoOfRetries(Integer noOfRetries) {
        this.noOfRetries = noOfRetries;
    }

    public String getConnectionPointName() {
        return connectionPointName;
    }

    public void setConnectionPointName(String connectionPointName) {
        this.connectionPointName = connectionPointName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getRouteUid() {
        return routeUid;
    }

    public void setRouteUid(Integer routeUid) {
        this.routeUid = routeUid;
    }

    public Integer getMmsObjectId() {
        return mmsObjectId;
    }

    public void setMmsObjectId(Integer mmsObjectId) {
        this.mmsObjectId = mmsObjectId;
    }

    public String getTelcoCode() {
        return telcoCode;
    }

    public void setTelcoCode(String telcoCode) {
        this.telcoCode = telcoCode;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
