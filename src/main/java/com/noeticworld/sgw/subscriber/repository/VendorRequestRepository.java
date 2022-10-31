package com.noeticworld.sgw.subscriber.repository;

import com.noeticworld.sgw.subscriber.model.VendorRequestsStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRequestRepository extends JpaRepository<VendorRequestsStateEntity, Long> {

    VendorRequestsStateEntity findVendorRequestsEntityByCorrelationidAndFetched(String correlationId, boolean isFetched);
}
