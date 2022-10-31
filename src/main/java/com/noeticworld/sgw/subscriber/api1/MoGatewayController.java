package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.config.RequestSender;
import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.service.MoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoGatewayController {

    Logger log = LoggerFactory.getLogger(MoGatewayController.class.getName());

    @Autowired RequestSender requestSender;
    @Autowired MoService moService;

    @RequestMapping(value = "/mo",method = RequestMethod.POST)
    public void moController(@RequestBody String stringParameters){

        log.info("SUBSCRIBER SERVICE | MOGATEWAYCONTROLLER CLASS | MESSAGE RECEIVED | "+stringParameters);
        moService.processRequest(stringParameters);

    }
}
