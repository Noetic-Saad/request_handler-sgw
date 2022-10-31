package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.LoginRequestDto;
import com.noeticworld.sgw.subscriber.service.LogInService;
import com.noeticworld.sgw.subscriber.service.LoginRequestBucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogInController {

    Logger log = LoggerFactory.getLogger(LogInController.class.getName());

    @Autowired
    LogInService logInService;
    @Autowired
    LoginRequestBucket loginRequestBucket;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> logIn(LoginRequestDto requestDto, HttpServletRequest request) {
        // Check Multiple requests in time range
        /*boolean isRequestAgain = loginRequestBucket.isRequestForMsisdnWithInTimeLimit(Long.parseLong(requestDto.getMsisdn()));
        if(isRequestAgain) {
            return ResponseEntity.badRequest().build();
        }*/

        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }

        log.info("SUBSCRIBER SERVICE | LOGINCONTROLLER CLASS | LOG IN REQUEST FOR | "+requestDto.getMsisdn());
        AppResponse appResponse = logInService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.LogIn);

        return ResponseEntity.accepted().body(appResponse);
    }

    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> logOut(LoginRequestDto requestDto, HttpServletRequest request) {

        log.info("SUBSCRIBER SERVICE | LOGINCONTROLLER CLASS | LOG OUT REQUEST FOR | "+requestDto.getMsisdn());

        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        AppResponse appResponse = logInService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.LogOut);
        return ResponseEntity.accepted().body(appResponse);
    }

    @PostMapping(value = "/auto-login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> autoLogin(LoginRequestDto requestDto, HttpServletRequest request) {

        log.info("SUBSCRIBER SERVICE | LOGINCONTROLLER CLASS | AUTO-LOGIN REQUEST FOR | "+requestDto.getMsisdn());
        log.info("SUBSCRIBER SERVICE | LOGINCONTROLLER CLASS | AUTO-LOGIN REQUEST FOR | "+requestDto.getSessionId());

        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        AppResponse appResponse = logInService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.AL01);
        return ResponseEntity.accepted().body(appResponse);
    }

}
