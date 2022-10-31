package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.RequestDto;
import com.noeticworld.sgw.subscriber.service.RequestStatusService;
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
@RequestMapping("/subscribe")
public class SubscriptionController {

    Logger log = LoggerFactory.getLogger(SubscriptionController.class.getName());

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> sub(RequestDto requestDto, HttpServletRequest request) {
        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        AppResponse appResponse = subscriptionService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.Sb01);
        log.info("SUBSCRIBER SERVICE | SUBSCRIPTIONCONTROLLER CLASS | SUB REQ FOR | "+requestDto.getMsisdn());
        return ResponseEntity.accepted().body(appResponse);
    }
}
