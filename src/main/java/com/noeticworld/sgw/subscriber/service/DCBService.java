package com.noeticworld.sgw.subscriber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.config.RequestSender;
import com.noeticworld.sgw.subscriber.dto.*;
import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Service class for DCB request
 */
@Service
public class DCBService {

    Logger log = LoggerFactory.getLogger(DCBService.class.getName());

    @Autowired
    private RequestSender requestSender;
    @Autowired
    private RequestStatusService requestStatusService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private String routingKey = "dcbapi";

    /***
     * @Desc will process request and return response
     * @param vendorPlanId
     * @param dcbRequestDto
     * @param action
     * @return
     */
    public AppResponse processRequest(String vendorPlanId, DcbRequestDto dcbRequestDto, Action action, HttpServletRequest httpServletRequest) throws JsonProcessingException {

        try {
            String correlationId = Base64.getEncoder().encodeToString((LocalDateTime.now().format(formatter) + UUID.randomUUID().toString()).getBytes());
            requestSender.send(routingKey, generateMessageJSON(vendorPlanId, dcbRequestDto, action, correlationId,httpServletRequest));
            requestStatusService.createDcbRequestState(correlationId,Long.parseLong(vendorPlanId));
log.info("SUBSCRIBER SERVICE | DCBSERVICE CLASS | ProcessRequest | " + correlationId +" | "+ dcbRequestDto.getMsisdn());
            return getSuccessfulResponse(correlationId);
        }catch (Exception e){
            log.error("SUBSCRIBER SERVICE | DCBSERVICE CLASS | Exception | "+ e.getMessage());
        }
        return getEmptyResponse();
    }

    public Object generateMessageJSON(String vendorPlanId, DcbRequestDto requestDto, Action action, String correlationId,HttpServletRequest httpServletRequest) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CustomDcbMessage customDcbMessage = new CustomDcbMessage();
        customDcbMessage.setCorrelationId(correlationId);
        customDcbMessage.setMsisdn(requestDto.getMsisdn());
        customDcbMessage.setAmount(requestDto.getAmount());
        customDcbMessage.setRemoteIpAddrres(httpServletRequest.getRemoteAddr());
        customDcbMessage.setServiceId(requestDto.getServiceId());
        customDcbMessage.setTransId(requestDto.getTransId());
        customDcbMessage.setVendorPlanId(vendorPlanId);
        customDcbMessage.setDateTime(LocalDateTime.now().toString());


        return objectMapper.writeValueAsString(customDcbMessage);
    }

    private AppResponse getSuccessfulResponse(String correlationId) {
        AppResponse appResponse = new AppResponse();
        appResponse.setCorrelationId(correlationId);
        appResponse.setCode("105");
        appResponse.setMessage("Received");
        return appResponse;
    }

    private AppResponse getEmptyResponse() {
        AppResponse appResponse = new AppResponse();
        appResponse.setMessage("Error Processing Request");
        return appResponse;
    }

}
