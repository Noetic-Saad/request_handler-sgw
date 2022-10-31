package com.noeticworld.sgw.subscriber.repository;

import com.noeticworld.sgw.subscriber.model.VendorDcbPlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorDcbPlanRepository extends JpaRepository<VendorDcbPlansEntity,Long> {
}
