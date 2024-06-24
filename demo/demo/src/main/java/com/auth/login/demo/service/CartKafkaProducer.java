package com.auth.login.demo.service;

import com.auth.login.demo.model.CartOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class CartKafkaProducer {

    private static final String TOPIC = "cart_topic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String userId, String productId, int quantity, String operation) {
        CartOperation cartOperation = new CartOperation(userId, productId, quantity, operation);
        this.kafkaTemplate.send(TOPIC, cartOperation);
    }
}
