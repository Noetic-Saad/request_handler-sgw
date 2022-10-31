package com.noeticworld.sgw.subscriber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.dto.RequestStatus;
import com.noeticworld.sgw.subscriber.repository.VendorRequestRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
;import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class SchedulerService {
    Logger log= LoggerFactory.getLogger(SchedulerService.class.getName());
    @Autowired
    VendorRequestRedisRepository vendorRequestRedisRepository;
    @Scheduled(cron = "0 0/30 * * * ?")
    public void expireRedisKeys(){
        List listofids = vendorRequestRedisRepository.findAll();
            log.info("Id Size : "+listofids.size());
        for (int i = 0; i <listofids.size() ; i++) {
            String  record = (String) listofids.get(i);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                RequestStatus requestStatus = objectMapper.readValue(record, RequestStatus.class);
                if(requestStatus.getCdatetime().before(Timestamp.valueOf(LocalDateTime.now().minusHours(1)))){
                    vendorRequestRedisRepository.deleteByCorrelationId(requestStatus.getCorrelationid());
                    log.info(requestStatus.getCorrelationid()+" : Deleted");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
