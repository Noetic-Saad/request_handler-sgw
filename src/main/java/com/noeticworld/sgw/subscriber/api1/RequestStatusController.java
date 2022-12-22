package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.ResponseTypeConstants;
import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import com.noeticworld.sgw.subscriber.repository.RedisRepository;
import com.noeticworld.sgw.subscriber.service.RequestStatusService;
import com.noeticworld.sgw.subscriber.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/requestStatus")
public class RequestStatusController {

Logger log = LoggerFactory.getLogger(RequestStatusController.class.getName());


    @Autowired
    private RequestStatusService requestStatusService;
    @Autowired
    RedisRepository redisRepository;

    @GetMapping("/{correlationId}")
    public ResponseEntity<AppResponse> getStatus(@PathVariable("correlationId") String correlationId) throws InterruptedException {
        AppResponse appResponse = requestStatusService.getRequestStatus(correlationId);
        log.info("REQUESTSTATUS | CorrelationId | "+correlationId + " | Status | "+ appResponse.toString());
        log.info("APPRESPONSE | "+appResponse.toString());
        return ResponseEntity.accepted().body(appResponse);
    }

//    @GetMapping("/dcb/{correlationId}")
    // below code is no longer in use as we have make another microservices for it
    public ResponseEntity<AppResponse> getDcbStatus(@PathVariable("correlationId") String correlationId, HttpServletRequest request) throws InterruptedException {
    AppResponse appResponse = requestStatusService.getDcRequestStatus(correlationId);
        log.info("REQUESTSTATUS DCB | CorrelationId | "+correlationId );
        System.out.println(appResponse.toString());
        return ResponseEntity.accepted().body(appResponse);
    }


}
