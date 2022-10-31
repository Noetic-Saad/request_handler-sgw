package com.noeticworld.sgw.subscriber.service;

import com.noeticworld.sgw.subscriber.model.VendorDcbPlansEntity;
import com.noeticworld.sgw.subscriber.repository.VendorDcbPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc service class to manage all the configurations
 */
@Service
public class ConfigurationDataManagerService {

    @Autowired
    private VendorDcbPlanRepository vendorDcbPlanRepository;
    Map<String, VendorDcbPlansEntity> vendorDcbPlansEntityMap = new HashMap<>();

    /***
     * @desc get All data from db table and put it into Map wiht serviceid Key
     */
    public void loadVendorDcbPlans() {
        List<VendorDcbPlansEntity> all = vendorDcbPlanRepository.findAll();
        all.forEach(vendorDcbPlansEntity -> vendorDcbPlansEntityMap.put(vendorDcbPlansEntity.getDcbServiceId(), vendorDcbPlansEntity));
    }

    /**
     * @desc load all configurations
     */
    public void bootstapAndCacheConfigurationData() {
        loadVendorDcbPlans();
    }

    /**
     * @desc will get dcb plan from map using serviceId
      * @param serviceId
     * @return
     */
    public VendorDcbPlansEntity getDcbPlan(String serviceId){
        return vendorDcbPlansEntityMap.get(serviceId);
    }

}
