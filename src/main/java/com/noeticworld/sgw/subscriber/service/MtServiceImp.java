package com.noeticworld.sgw.subscriber.service;

import com.noeticworld.sgw.subscriber.dto.MtResponse;
import com.noeticworld.sgw.subscriber.util.MtClient;
import com.noeticworld.sgw.subscriber.util.MtProperties;
import com.noeticworld.sgw.subscriber.util.MtReqeustBody;
import com.noeticworld.sgw.subscriber.util.Mtservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class MtServiceImp implements Mtservice {

    @Autowired
    MtClient mtClient;



    public MtResponse sendMt(MtReqeustBody mtReqeustBody) {

        MtProperties mtProperties = new MtProperties();
        mtProperties.setShortCode("3444");
        mtProperties.setPassword("g@m3now");
        mtProperties.setUsername("gamenow@noetic");
        mtProperties.setServiceId("1061");
        mtProperties.setData(mtReqeustBody.getMessage());
        if(!(validateMsisdn(mtReqeustBody.getMsisdn()))){
            return  createMtResponse("401","Invalid Msisdn");
        }
        mtProperties.setMsisdn(mtReqeustBody.getMsisdn());
        sendMt(mtProperties);

        return createMtResponse("202","Message Sent");

    }

    private boolean validateMsisdn(String msisdn) {
        if(msisdn.length()==12 && msisdn.substring(0,2).equals("92") && msisdn.substring(2,3).equals("3"))
            return true;
        return false;
    }

    private MtResponse createMtResponse(String code,String message){
        MtResponse mtResponse = new MtResponse();
        mtResponse.setCode(code);
        mtResponse.setMessage(message);
        return mtResponse;
    }

    @Override
    public void sendMt(MtProperties mtProperties) {
         mtClient.sendMt(mtProperties);
    }
}

