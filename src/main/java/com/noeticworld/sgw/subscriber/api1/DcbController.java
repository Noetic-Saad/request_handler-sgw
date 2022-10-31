package com.noeticworld.sgw.subscriber.api1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noeticworld.sgw.subscriber.dto.Action;
import com.noeticworld.sgw.subscriber.dto.AppResponse;
import com.noeticworld.sgw.subscriber.dto.DcbRequestDto;
import com.noeticworld.sgw.subscriber.dto.MtResponse;
import com.noeticworld.sgw.subscriber.service.DCBService;
import com.noeticworld.sgw.subscriber.service.MtServiceImp;
import com.noeticworld.sgw.subscriber.util.MtClient;
import com.noeticworld.sgw.subscriber.util.MtReqeustBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/***
 * Http EndPoint for dcb transactions
 * @RestController
 */
@RestController
public class DcbController {

    Logger log = LoggerFactory.getLogger(DcbController.class.getName());

    @Autowired private DCBService dcbService;

    @Autowired
    private MtServiceImp mtServiceImp;

    @Autowired
    MtClient mtClient;

    /***
     *
     * @param dcbRequestDto
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/charge", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> dcbRequest(DcbRequestDto dcbRequestDto, HttpServletRequest request){


        AppResponse appResponse = null;
        log.info("DCB CONTROLLER | Charging request for -> "+dcbRequestDto.getMsisdn() + " | VendorPlanID | "+ request.getHeader("vendorPlanId"));
        // If vendorPlanId not Found will return not found error message
        if(request.getHeader("vendorPlanId") == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            appResponse = dcbService.processRequest(request.getHeader("vendorPlanId"), dcbRequestDto, Action.DCB, request);
        } catch (JsonProcessingException e) {
            log.error("Exception Caught Here " + e.getMessage());
        }

        return ResponseEntity.accepted().body(appResponse);
    }

    @CrossOrigin
    @PostMapping(value = "/mt", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MtResponse> mtReqeust(MtReqeustBody mtReqeustBody, HttpServletRequest request){


        MtResponse mtResponse = null;
        log.info("MTREQUEST | MSISDN | " + mtReqeustBody.getMsisdn() + " | SERVICEID | " +mtReqeustBody.getServiceId() + " | VENDORPLANID | "+request.getHeader("vendorPlanId"));
        // If vendorPlanId not Found will return not found error message
        if(request.getHeader("vendorPlanId") == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            mtResponse = mtServiceImp.sendMt(mtReqeustBody);
        } catch (Exception e) {
            log.error("Exception Caught Here " + e.getMessage());
        }

        return ResponseEntity.ok().body(mtResponse);
    }


}
