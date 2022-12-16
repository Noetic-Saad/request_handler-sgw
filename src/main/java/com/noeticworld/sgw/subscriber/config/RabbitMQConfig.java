
package com.noeticworld.sgw.subscriber.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {

//    @Bean
//    Queue subscriptionQueue() {
//        return new Queue("subscriptionQueue", false);
//    }

    @Bean
    @Primary
    Queue JazzChargingQueue() {
        return new Queue("JazzChargingQueue", false);
    }

    @Bean
    Queue ZongChargingQueue() {
        return new Queue("ZongChargingQueue", false);
    }

    @Bean
    Queue mogatewayQueue() {
        return new Queue("mogatewayQueue", false);
    }


    @Bean
    Queue dcbapiQueue() {
        return new Queue("dcbapiQueue", false);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange("direct-exchange");
    }

//    @Bean
//    Binding subscriptionBinding(Queue subscriptionQueue, DirectExchange exchange) {
//        return BindingBuilder.bind(subscriptionQueue).to(exchange).with("subscription");
//    }

    @Bean
    Binding JazzChargingBinding(Queue subscriptionQueue, DirectExchange exchange) {
        return BindingBuilder.bind(subscriptionQueue).to(exchange).with("JazzCharging");
    }

    @Bean
    Binding ZongChargingBinding(Queue mogatewayQueue, DirectExchange exchange) {
        return BindingBuilder.bind(mogatewayQueue).to(exchange).with("ZongCharging");
    }

    @Bean
    Binding mogatewayBinding(Queue mogatewayQueue, DirectExchange exchange) {
        return BindingBuilder.bind(mogatewayQueue).to(exchange).with("mogateway");
    }

    @Bean
    Binding dcbapiBinding(Queue dcbapiQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dcbapiQueue).to(exchange).with("dcbapi");
    }

}