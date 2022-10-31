package com.noeticworld.sgw.subscriber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpRequestBucket {
    private static final Logger log = LoggerFactory.getLogger(OtpRequestBucket.class);

    private final Map<Long, Timestamp> bucket;
    private final long timeLimit;

    public OtpRequestBucket() {
        bucket = new ConcurrentHashMap<>();
        timeLimit = 10000;
    }

    public boolean isRequestForMsisdnWithInTimeLimit(long msisdn) {
        if (bucket.get(msisdn) == null) {
            // ***** MSISDN doesn't exist, add it *****
            bucket.put(msisdn, Timestamp.from(Instant.now()));
            log.info("OtpRequestBucket class | !exist | " + msisdn + " | " + Timestamp.from(Instant.now()) + " | bucket size: " + bucket.size());
        } else {
            // ***** MSISDN already exists *****

            // Get difference of current timestamp & stored timestamp
            long differenceInTime = Timestamp.from(Instant.now()).getTime() - bucket.get(msisdn).getTime();

            if (differenceInTime <= timeLimit) {
                // Block request
                log.info("OtpRequestBucket class | blocked | " + msisdn + " | " + Timestamp.from(Instant.now()) + " | bucket size: " + bucket.size());
                return true;
            } else {
                // Allow request as time limit > blocked time limit
                bucket.put(msisdn, Timestamp.from(Instant.now()));
                log.info("OtpRequestBucket class | allowed | " + msisdn + " | " + Timestamp.from(Instant.now()) + " | bucket size: " + bucket.size());
            }
        }

        bucket.entrySet().removeIf(e -> (Timestamp.from(Instant.now()).getTime() - e.getValue().getTime()) > timeLimit);
        return false;
    }

}
