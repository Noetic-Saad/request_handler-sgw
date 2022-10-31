package com.noeticworld.sgw.subscriber.api1;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/test")
public class TestController {

    // only one client, singleton, better puts it in a factory,
    // multiple instances will create more memory.
    private final OkHttpClient httpClient = new OkHttpClient();

    @GetMapping("/test")
    public void testCall() {
        LocalDate yourDate = LocalDate.of(2021, 07, 17);

        Timestamp rStartTime = Timestamp.valueOf(yourDate.atTime(LocalTime.of(00, 00, 00)));
        Timestamp rEndTime = Timestamp.valueOf(yourDate.atTime(LocalTime.of(21, 00, 00)));

        Request request = new Request.Builder()
                .url("http://192.168.127.62:10001/postback?rStart=" + rStartTime.getTime() + "&rEnd=" + rEndTime.getTime())
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            System.out.println("|||||||||||||||| RESPONSE CODE: " + response.code());
            if (response.isSuccessful())
                System.out.println("|||||||||||||| SUCCESSFUL REQUEST |||||||||||||");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
