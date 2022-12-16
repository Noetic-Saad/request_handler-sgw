package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.RequestDto;
import com.noeticworld.sgw.subscriber.model.OtpRecordsEntity;
import com.noeticworld.sgw.subscriber.repository.OtpRecordRepository;
import com.noeticworld.sgw.subscriber.repository.RedisRepository;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    RedisRepository redisRepository;

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
        code = getTopOtpRecordFromMsidn(msisdn);
//        code=otpRecordRepository.findTopByMsisdn(msisdn);
        System.out.println("Fetch OTP Record For Msisdn : "+code);
        return code;
    }

    private Long getTopOtpRecordFromMsidn(Long msisdn)
    {
        List<OtpRecordsEntity> otpRecordsEntityList = redisRepository.findAllOTPOfMsisdn(String.valueOf(msisdn));
        System.out.println(otpRecordsEntityList.size());
        List<OtpRecordsEntity> recordsEntities = new ArrayList<>();
        for (OtpRecordsEntity otpRecordsEntity : otpRecordsEntityList) {
            System.out.println("OTP RECORDS ENTITY : "+otpRecordsEntity.getMsisdn() + " Equals : " + msisdn);
            if(otpRecordsEntity.getMsisdn().equals(msisdn))
            {
                recordsEntities.add(otpRecordsEntity);
            }
        }
        System.out.println(recordsEntities.size());
        Collections.sort(recordsEntities, new Comparator<OtpRecordsEntity>() {
            public int compare(OtpRecordsEntity o1, OtpRecordsEntity o2) {
                if (o1.getCdate() == null || o2.getCdate() == null)
                    return 0;
                return o1.getCdate().compareTo(o2.getCdate());
            }
        });

        System.out.println(recordsEntities.get(0).getOtpNumber());
        return new Long(otpRecordsEntityList.get(0).getOtpNumber());
    }
}
