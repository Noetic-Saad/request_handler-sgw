package com.noeticworld.sgw.subscriber.service;

import com.noeticworld.sgw.subscriber.model.VendorDcbPlansEntity;
import com.noeticworld.sgw.subscriber.util.HeResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class HeService {

    @Autowired private ConfigurationDataManagerService configurationDataManagerService;

    /***
     * @desc method will process He request find dcb plan using serviceId
     * @param dcbServiceId
     * @return
     */
    public void processHeRequest(String dcbServiceId, HttpServletResponse response) throws IOException {

        VendorDcbPlansEntity entity = configurationDataManagerService.getDcbPlan(dcbServiceId);

        if(entity.getOperatorId() == 1){
            getJazzHe(response);
        }else if (entity.getOperatorId()==4){
            getZongHe(response);
        }else if(entity.getOperatorId()==5){
            getTelenorHe();
        }else {
            createFailedResponse();
        }
    }


    private HeResponse getTelenorHe() {
        return createFailedResponse();
    }

    private void getZongHe(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://210.56.27.20:8080/getHeader");
    }

    /***
     * @desc get msisdn from Jazz Header
     * @return
     * @throws IOException
     */
    private void getJazzHe(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://210.56.27.19:8080/getHeader");
    }

    private HeResponse createFailedResponse() {
        HeResponse heResponse = new HeResponse();
        heResponse.setCode(121);
        heResponse.setMessage("msisdn not found");
        return heResponse;
    }
}
