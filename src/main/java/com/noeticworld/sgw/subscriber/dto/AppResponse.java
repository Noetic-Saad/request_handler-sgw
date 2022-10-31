package com.noeticworld.sgw.subscriber.dto;

public class AppResponse {

    //TODO Changes Made By Rizwan
    private String code;
    private String message;
    private String correlationId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "AppResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
