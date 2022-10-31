package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.RequestDto;
import com.noeticworld.sgw.subscriber.repository.OtpRecordRepository;
import com.noeticworld.sgw.subscriber.service.OtpRequestBucket;
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
@RequestMapping("/otp-verification")
public class OtpVerificationController {

    Logger log = LoggerFactory.getLogger(OtpVerificationController.class.getName());

    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    OtpRecordRepository otpRecordRepository;
    @Autowired
    OtpRequestBucket otpRequestBucket;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> otpVerification(RequestDto requestDto, HttpServletRequest request) {
        // Check Multiple requests in time range
        /*boolean isRequestAgain = otpRequestBucket.isRequestForMsisdnWithInTimeLimit(Long.parseLong(requestDto.getMsisdn()));
        if(isRequestAgain) {
            return ResponseEntity.badRequest().build();
        }*/

        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }

        AppResponse appResponse = subscriptionService.processRequest(request.getHeader("vendorPlanId"), requestDto, Action.OtpVerification);
        log.info("SUBSCRIBER SERVICE |  OTPVERFICATIONCONTROLLER CLASS | OTP-SENT FOR | "+requestDto.getMsisdn());
        return ResponseEntity.accepted().body(appResponse);
    }
    @PostMapping("/fetchOtp")
    public Long fetchotpFromDatabase(long msisdn) throws InterruptedException {
        Thread.sleep(3000);
        long code;
        code=otpRecordRepository.findTopByMsisdn(msisdn);
        System.out.println("Fetch OTP Record For Msisdn : "+code);
        return code;
    }
}
