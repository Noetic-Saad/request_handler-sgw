package com.noeticworld.sgw.subscriber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppBootstapListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ConfigurationDataManagerService configurationDataManagerService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        configurationDataManagerService.bootstapAndCacheConfigurationData();
    }
}
