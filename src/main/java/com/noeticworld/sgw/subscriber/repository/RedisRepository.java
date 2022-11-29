package com.noeticworld.sgw.subscriber.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.dto.RequestStatus;
import com.noeticworld.sgw.subscriber.model.OtpRecordsEntity;
import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RedisRepository {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    private String VendorRequestEntityKEY = "VENDOR_ACESS_KEY";
    private String OTPEntityKEY = "OTP_KEY";

    Logger log = LoggerFactory.getLogger(RedisRepository.class.getName());

    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void saveVendorRequest(String key, String entity){
        hashOperations.put(VendorRequestEntityKEY, key, entity);
        log.info("REDISREPOSITORY || SAVEVENDORREQUEST || " + entity.toString());
    }

    public void saveOtpRecord(String key,String entity){
        hashOperations.put(OTPEntityKEY, key, entity);
        log.info("ZFTER || REDISREPOSITORY || SAVEOTPRECORD || " + hashOperations.values(OTPEntityKEY));
    }

    public List<OtpRecordsEntity> findAllOTPOfMsisdn(String Msisdn){
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
        log.info("REDISREPOSITORY || FINDALLOTPOFMSISDN || " + otprecordlist.toString());
        return otprecordlist;
    }

    public VendorRequestsStateEntity findVendorRequestStatus(String CorelationId){

        VendorRequestsStateEntity vendorRequestStatusEntity = null;
        String vendor = (String) hashOperations.get(VendorRequestEntityKEY, CorelationId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            vendorRequestStatusEntity = objectMapper.readValue(vendor, VendorRequestsStateEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("REDISREPOSITORY || FINDOTPRECORD || " + vendorRequestStatusEntity.toString());
        return vendorRequestStatusEntity;
    }

    public VendorRequestsStateEntity findVendorRequestStatusFalse(String CorelationId){

        VendorRequestsStateEntity vendorRequestStatusEntity = null;
        String vendor = (String) hashOperations.get(VendorRequestEntityKEY, CorelationId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            vendorRequestStatusEntity = objectMapper.readValue(vendor, VendorRequestsStateEntity.class);
            if(vendorRequestStatusEntity.getFetched() == true)
            {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("REDISREPOSITORY || FINDREQUESTSTATUS || " + vendorRequestStatusEntity.toString());
        return vendorRequestStatusEntity;
    }

}
