package com.noeticworld.sgw.subscriber.repository;

import com.noeticworld.sgw.subscriber.model.OtpRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRecordRepository extends JpaRepository<OtpRecordsEntity,Integer> {
    @Query(value = "select otp_number from otp_records where msisdn = :msisdn order by id desc limit 1",nativeQuery = true)
    long findTopByMsisdn(@Param("msisdn") long msisdn);

}
