package com.noeticworld.sgw.subscriber.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.model.OtpRecordsEntity;
import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RedisRepository {

    private HashOperations hashOperations;

    @Autowired
    private RedisTemplate redisTemplate;

    private String VendorRequestEntityKEY = "VENDOR_ACESS_KEY";
    private String OTPEntityKEY = "OTP_KEY";

    Logger log = LoggerFactory.getLogger(RedisRepository.class.getName());

    @Autowired
    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

//    public void saveVendorRequest(VendorRequestsStateEntity requestStatus){
//        hashOperations.put(VendorRequestEntityKEY, requestStatus.getCorrelationid(), requestStatus);
//        log.info("REDISREPOSITORY SAVEVENDORREQUEST || VENDOREEQUEST ", requestStatus.toString());
//    }

    public void saveOtpRecord(String key,String entity){
        hashOperations.put(OTPEntityKEY, key, entity);
        log.info("REDISREPOSITORY SAVEOTPRECORDS || OTPRECORD " + entity);
    }

    public List<OtpRecordsEntity> findAllOTPOfMsisdn(String Msisdn){
        log.info("REDISREPOSITORY FINDALLOTPOFMSISDN || MSISDN " + Msisdn);
        List<OtpRecordsEntity> otprecordlist = new ArrayList<>();
        List listofids =  hashOperations.values(OTPEntityKEY);
        for (int i = 0; i <listofids.size() ; i++) {
            String  record = (String) listofids.get(i);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                OtpRecordsEntity requestStatus = objectMapper.readValue(record, OtpRecordsEntity.class);
                otprecordlist.add(requestStatus);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("REDISREPOSITORY FINDALLOTPOFMSISDN || OTPRECORDLIST " + otprecordlist.toString());
        return otprecordlist;
    }

//    public VendorRequestsStateEntity findVendorRequestStatus(String CorelationId){
//        log.info("REDISREPOSOTORY FINDVENDORREQUESTSTATUS || CORELATIONID " + CorelationId);
//        return (VendorRequestsStateEntity) hashOperations.get(VendorRequestEntityKEY, CorelationId);
////        ObjectMapper objectMapper = new ObjectMapper();
////        try {
////            VendorRequestsStateEntity vendorRequestStatusEntity = objectMapper.readValue(vendor, VendorRequestsStateEntity.class);
////            log.info("REDISREPOSITORY FINDVENDORREQUESTSTATUS || VENDORREQUEST " + vendorRequestStatusEntity.toString());
////            return vendorRequestStatusEntity;
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//    }

//    public VendorRequestsStateEntity findVendorRequestStatusFalse(String CorelationId){
//
//        VendorRequestsStateEntity vendorRequestStatusEntity = null;
//        vendorRequestStatusEntity = (VendorRequestsStateEntity) hashOperations.get(VendorRequestEntityKEY, CorelationId);
////        System.out.println(vendor);
////        ObjectMapper objectMapper = new ObjectMapper();
//        if(vendorRequestStatusEntity == null)
//        {
//            return null;
//        }
//        else
//        {
////                vendorRequestStatusEntity = objectMapper.readValue(vendor, VendorRequestsStateEntity.class);
//            if(vendorRequestStatusEntity.getFetched() == true)
//            {
//                return null;
//            }
//        }
//        log.info("REDISREPOSITORY || FINDREQUESTSTATUSFALSE || " + vendorRequestStatusEntity.toString());
//        return vendorRequestStatusEntity;
//    }

}
