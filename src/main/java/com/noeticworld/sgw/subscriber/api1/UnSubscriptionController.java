package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.RequestDto;
import com.noeticworld.sgw.subscriber.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/unsubscribe")
public class UnSubscriptionController {

    Logger log = LoggerFactory.getLogger(UnSubscriptionController.class.getName());

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> unSub(RequestDto requestDto, HttpServletRequest request) {
        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        AppResponse appResponse = subscriptionService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.Unsb01);
        log.info("SUBSCRIBER SERVICE |  UNSUBSCRIPTIONCONTROLLER CLASS | UNSUB REQUEST BY WEB FOR | "+requestDto.getMsisdn());
        return ResponseEntity.accepted().body(appResponse);
    }

    @PostMapping(value = "/telco-unsub", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> telcoUnsub(RequestDto requestDto, HttpServletRequest request) {
        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        AppResponse appResponse = subscriptionService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.Unsb02);
        log.info("SUBSCRIBER SERVICE |  UNSUBSCRIPTIONCONTROLLER CLASS | UNSUB REQUEST BY TELECO FOR | "+requestDto.getMsisdn());
        return ResponseEntity.accepted().body(appResponse);
    }

    @PostMapping(value = "/mounsub", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> moUnSub(RequestDto requestDto) {
        log.info("SUBSCRIBER SERVICE |  UNSUBSCRIPTIONCONTROLLER CLASS | UNSUB REQUEST BY MO FOR | "+requestDto.getMsisdn());
        AppResponse appResponse = subscriptionService.processRequest("1", requestDto, Action.Unsb01);
        return ResponseEntity.accepted().body(appResponse);
    }

}
