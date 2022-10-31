package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.RequestDto;
import com.noeticworld.sgw.subscriber.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/block")
public class BlockingController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(value = "/vendor", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> vendorUnSub(RequestDto requestDto, HttpServletRequest request) {
        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        //add log here
        System.out.println("SUBSCRIBER|BLK01|" + requestDto.getMsisdn());
        AppResponse appResponse = subscriptionService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.Blk01);
        return ResponseEntity.accepted().body(appResponse);
    }

    @PostMapping(value = "/telco", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> telcoUnSub(RequestDto requestDto, HttpServletRequest request) {
        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        System.out.println("SUBSCRIBER|BLK02|" + requestDto.getMsisdn());
        //TODO add log here
        AppResponse appResponse = subscriptionService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.Blk02);
        return ResponseEntity.accepted().body(appResponse);
    }

}
