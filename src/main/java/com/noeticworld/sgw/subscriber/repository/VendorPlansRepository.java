package com.noeticworld.sgw.subscriber.repository;

import com.noeticworld.sgw.subscriber.model.VendorPlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorPlansRepository extends JpaRepository<VendorPlansEntity,Integer> {

    @Query(value = "SELECT vp.operator_id FROM public.vendor_plans vp where id=:vendorPlanId" ,nativeQuery = true)
    Integer getOperator(Integer vendorPlanId);
}
