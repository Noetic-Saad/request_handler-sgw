package com.noeticworld.sgw.subscriber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noeticworld.sgw.subscriber.config.RequestSender;
import com.noeticworld.sgw.subscriber.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MoService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private String  routingKey = "mogateway";

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    private RequestSender requestSender;
    @Autowired private RequestStatusService requestStatusService;

    public AppResponse processRequest(String stringParameters) {
        try {
            String correlationId = Base64.getEncoder().encodeToString((LocalDateTime.now().format(formatter) + UUID.randomUUID().toString()).getBytes());
            requestSender.send(routingKey,generateMessageJSON(stringParameters, correlationId));
            return getSuccessfulResponse(correlationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEmptyResponse();
    }

    private Object generateMessageJSON(String stringParameters,String correlationId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomMoMessage customMoMessage = parseUrlEncodedParameterString(stringParameters);
        customMoMessage.setCorrelationId(correlationId);
        return objectMapper.writeValueAsString(customMoMessage);
    }

    private AppResponse getSuccessfulResponse(String correlationId) {
        AppResponse appResponse = new AppResponse();
        appResponse.setCorrelationId(correlationId);
        appResponse.setCode("105");
        appResponse.setMessage("Received");
        return appResponse;
    }

    private AppResponse getEmptyResponse() {
        AppResponse appResponse = new AppResponse();
        appResponse.setMessage("Error Processing Request");
        return appResponse;
    }

    private CustomMoMessage parseUrlEncodedParameterString(String parameterString) {
        ArrayList<String> nameValuePairs = getNameValuePairs(parameterString);
        CustomMoMessage_ customMoMessage = new CustomMoMessage_();
        CustomMoMessage customMoMessage1 = new CustomMoMessage();
        Iterator i$ = nameValuePairs.iterator();

        while(i$.hasNext()) {
            String nameValuePair = (String)i$.next();
            String parameterName = "";
            String value = "";
            StringTokenizer st = new StringTokenizer(nameValuePair, "=");
            if (st.hasMoreTokens()) {
                parameterName = st.nextToken();
            }

            if (st.hasMoreTokens()) {
                value = st.nextToken();
            }

            parameterName = this.decode(parameterName);
            value = this.decode(value);
            //return this.processNameValuePair(parameterName, value);
            try {
                if (parameterName.equals("shortcode")) {
                    customMoMessage.setShortcode(value);
                    customMoMessage1.setShortcode(value);
                } else if (parameterName.equals("createSession")) {
                    //customMoMessage.setCreateSession(Boolean.parseBoolean(value));
                } else if (parameterName.equals("data")) {
                    customMoMessage.setSmsText(value);
                    customMoMessage1.setSmsText(value);
                } else if (parameterName.equals("isAccepted")) {
                    //customMoMessage.setIsAccepted(Boolean.parseBoolean(value));
                } else if (parameterName.equals("message_type")) {
                    customMoMessage.setMessageType(Integer.parseInt(value));
                } else if (parameterName.equals("msisdn")) {
                    customMoMessage.setMsisdn(value);
                    customMoMessage1.setMsisdn(value);
                } else if (parameterName.equals("operatorId")) {
                    customMoMessage.setConnectivityPointId(Integer.parseInt(value));
                    customMoMessage1.setConnectivityPointId(Integer.parseInt(value));
                } else if (parameterName.equals("priceGroup")) {
                    customMoMessage.setPriceGroup(Integer.parseInt(value));
                } else if (parameterName.equals("timeoutInterval")) {
                    //  customMoMessage.setSessionTimeoutInterval(Integer.parseInt(value));
                } else if (parameterName.equals("title")) {
                    customMoMessage.setSubject(value);
                } else if (parameterName.equals("transId")) {
                    customMoMessage.setSmsId(Integer.parseInt(value));
                    customMoMessage1.setSmsId(Integer.parseInt(value));
                } else if (parameterName.equals("responseCode")) {
                    //customMoMessage.setResponseString(value);
                } else if (parameterName.equals("requireResponse")) {
                    //customMoMessage.setRequireResponse(Boolean.valueOf(value));
                } else if (parameterName.equals("refNo")) {
                    customMoMessage.setRefNo(value);
                } else if (parameterName.equals("priceGroup")) {
                    customMoMessage.setPriceGroup(Integer.valueOf(value));
                } else if (parameterName.equals("connPointName")) {
                    customMoMessage.setConnectionPointName(value);
                } else if (parameterName.equals("extraParameters")) {
                    ArrayList<String> nameValuePairs1 = getNameValuePairs(value);

                    String key;
                    String parameterValue;
                    for(Iterator i$1 = nameValuePairs1.iterator();
                        i$.hasNext();
                        customMoMessage.getExtraParameters().put(CustomMoMessage_.ExtraParameterKey.valueOf(key), parameterValue)) {
                        String nameValuePair1 = (String)i$1.next();
                        key = "";
                        parameterValue = "";
                        StringTokenizer st1 = new StringTokenizer(nameValuePair1, "=");
                        key = this.decode(st1.nextToken());
                        if (st1.hasMoreTokens()) {
                            parameterValue = this.decode(st1.nextToken());
                        } else {
                            parameterValue = "";
                        }
                    }
                }
            } catch (Exception var9) {

            }

        }
        return customMoMessage1;
    }


    private static ArrayList<String> getNameValuePairs(String parameterString) {
        ArrayList<String> nameValuePairList = new ArrayList();
        StringTokenizer st = new StringTokenizer(parameterString, "&");

        while(st.hasMoreTokens()) {
            String nameValuePair = st.nextToken();
            nameValuePairList.add(nameValuePair);
        }

        return nameValuePairList;
    }

    private String decode(String string) {
        String decodedString = "";

        try {
            decodedString = URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return decodedString;
    }

    private static String getNameValuePair(String parameterName, String value) {
        String nameValuePair = encode(parameterName) + "=" + encode(value);
        return nameValuePair;
    }

    private static String encode(String string) {
        String encodedString = "";

        try {
            encodedString = URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return encodedString;
    }
}
