package com.noeticworld.sgw.subscriber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.config.RequestSender;
import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.CustomMessage;
import com.noeticworld.sgw.subscriber.dto.RequestDto;
import com.noeticworld.sgw.subscriber.repository.VendorPlansRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

public class OtpVerificationService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    //    private String routingKey = "subscription";

    private String JAZZroutingKey = "JazzCharging";
    private String ZONGroutingKey = "ZongCharging";
    @Autowired
    private VendorPlansRepository vendorPlansRepository;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    private RequestSender requestSender;
    @Autowired private RequestStatusService requestStatusService;

    public AppResponse processRequest(String vendorPlanId, RequestDto requestDto, Action action) {
        try {
            String correlationId = Base64.getEncoder().encodeToString((LocalDateTime.now().format(formatter) + UUID.randomUUID().toString()).getBytes());
            //Get Operator Id from Vendors Plan where Vendor Plan Id is Equal to Id
            Integer operator = vendorPlansRepository.getOperator(Integer.parseInt(vendorPlanId));
            if(operator == 1)
            {
                requestSender.send(JAZZroutingKey,generateMessageJSON(vendorPlanId, requestDto, action, correlationId));
                requestStatusService.createRequestState(correlationId, Long.parseLong(vendorPlanId));
            }
            else if (operator == 4)
            {
                requestSender.send(ZONGroutingKey,generateMessageJSON(vendorPlanId, requestDto, action, correlationId));
                requestStatusService.createRequestState(correlationId, Long.parseLong(vendorPlanId));
            }


            return getSuccessfulResponse(correlationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEmptyResponse();
    }

    private Object generateMessageJSON(String vendorPlanId, RequestDto requestDto, Action action, String correlationId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomMessage customMessage = new CustomMessage();
        customMessage.setAction(action.toString());
        customMessage.setCorelationId(correlationId);
        customMessage.setDateTime(String.valueOf(System.currentTimeMillis()));
        customMessage.setMsisdn(requestDto.getMsisdn());
        customMessage.setVendorPlanId(vendorPlanId);
        customMessage.setTrackerId(requestDto.getTrackerId());
        customMessage.setOtp(requestDto.isOtp());

        return objectMapper.writeValueAsString(customMessage);
    }

    private AppResponse getSuccessfulResponse(String correlationId) {
        AppResponse appResponse = new AppResponse();
        appResponse.setCorrelationId(correlationId);
        // TODO Changes Made By Rizwan
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
