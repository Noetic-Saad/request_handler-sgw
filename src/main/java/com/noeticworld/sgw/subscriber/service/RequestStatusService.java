package com.noeticworld.sgw.subscriber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.RequestStatus;
import com.noeticworld.sgw.subscriber.dto.ResponseTypeConstants;
import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import com.noeticworld.sgw.subscriber.repository.RedisRepository;
import com.noeticworld.sgw.subscriber.repository.VendorRequestRedisRepository;
import com.noeticworld.sgw.subscriber.repository.VendorRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class RequestStatusService {

    Logger log = LoggerFactory.getLogger(RequestStatusService.class.getName());

    @Autowired
    private VendorRequestRepository vendorRequestRepository;
    @Autowired
    private VendorRequestRedisRepository vendorRequestRedisRepository;

    @Autowired
    RedisRepository redisRepository;

    public AppResponse getRequestStatus(String correlationId) {

        VendorRequestsStateEntity requestStatus = null;
        requestStatus = redisRepository.findVendorRequestStatusFalse(correlationId);
        if(requestStatus == null)
        {
            vendorRequestRepository.findVendorRequestsEntityByCorrelationidAndFetched(correlationId, false);
        }
        log.info("SUBSCRIBER SERVICE | REQUESTSTATUSSERVCIE CLASS | REQUEST CAME FOR GAME | " + correlationId);
        AppResponse appResponse = new AppResponse();
        if (requestStatus != null) {
            appResponse.setCode(requestStatus.getResultStatus());
            appResponse.setCorrelationId(correlationId);
            appResponse.setMessage(requestStatus.getDescription());
            if (!requestStatus.getFetched()) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    redisRepository.saveVendorRequest(requestStatus.getCorrelationid(), objectMapper.writeValueAsString(requestStatus));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                vendorRequestRepository.save(requestStatus);
            }
        }
        return appResponse;
    }

    public AppResponse getDcRequestStatus(String correlationId) {
        log.info("SUBSCRIBER SERVICE | REQUESTSTATUSSERVCIE CLASS | REQUEST CAME FOR DCB | " + correlationId);
        String msg = vendorRequestRedisRepository.findByCorrelationId(correlationId);
        log.info("CORRELATIONID IN REDIS | " + msg);
        if (msg == null) {
            AppResponse appResponse = new AppResponse();
            appResponse.setCode(String.valueOf(000));
            appResponse.setCorrelationId(correlationId);
            appResponse.setMessage("correlationId not found");
            return appResponse;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        RequestStatus requestStatus = null;
        try {
            requestStatus = objectMapper.readValue(msg, RequestStatus.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // log.info("SUBSCRIBER SERVICE | REQUESTSTATUSSERVCIE CLASS | REQUEST CAME FOR DCB | "+correlationId);
        AppResponse appResponse = new AppResponse();
        if (requestStatus != null) {
            appResponse.setCode(requestStatus.getResultStatus());
            appResponse.setCorrelationId(correlationId);
            appResponse.setMessage(requestStatus.getDescription());
        }
        return appResponse;
    }


    public void createDcbRequestState(String correlationId, long vendorPlanId) {

        RequestStatus requestStatus = new RequestStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        requestStatus.setCorrelationid(correlationId);
        requestStatus.setVendorPlanId(vendorPlanId);
        requestStatus.setFetched(false);
        requestStatus.setCdatetime(new Timestamp(new Date().getTime()));
        requestStatus.setResultStatus(ResponseTypeConstants.REQUEST_IN_PROGRESS);
        requestStatus.setDescription(ResponseTypeConstants.REQUEST_IN_PROGRESS_DESCRIPTION);
        try {
            vendorRequestRedisRepository.save(requestStatus.getCorrelationid(), objectMapper.writeValueAsString(requestStatus));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("SUBSCRIBER SERVICE | REQUESTSTATUSSERVCIE CLASS | CORRELATION ID SAVED DCB | " + correlationId);
    }

    public void createRequestState(String correlationId, long vendorPlanId) {

        VendorRequestsStateEntity requestStatus = new VendorRequestsStateEntity();
        requestStatus.setCorrelationid(correlationId);
        requestStatus.setVendorPlanId(vendorPlanId);
        requestStatus.setFetched(false);
        requestStatus.setCdatetime(new Timestamp(new Date().getTime()));
        requestStatus.setResultStatus(ResponseTypeConstants.REQUEST_IN_PROGRESS);
        requestStatus.setDescription(ResponseTypeConstants.REQUEST_IN_PROGRESS_DESCRIPTION);
        vendorRequestRepository.save(requestStatus);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            redisRepository.saveVendorRequest(requestStatus.getCorrelationid(), objectMapper.writeValueAsString(requestStatus));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("SUBSCRIBER SERVICE | REQUESTSTATUSSERVCIE CLASS | CORRELATION ID SAVED  | " + correlationId);
    }
}
