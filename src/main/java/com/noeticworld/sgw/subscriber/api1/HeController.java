package com.noeticworld.sgw.subscriber.api1;

import com.noeticworld.sgw.subscriber.service.HeService;
import com.noeticworld.sgw.subscriber.util.HeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Http EndPoint for H.E
 * @RestController
 */
@RestController
public class HeController {


    @Autowired private HeService heService;

    /***
     *
     * @param serviceId
     * @param request
     * @return
     */
    @PostMapping(value = "/he", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void dcbRequest(String serviceId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // If vendorPlanId not Found will return not found error message
        if(request.getHeader("vendorPlanId") == null) {
            ResponseEntity.notFound().build();
        }
        heService.processHeRequest(serviceId,response);
    }
}
