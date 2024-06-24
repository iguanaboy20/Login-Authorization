package com.auth.login.demo.service;

import com.auth.login.demo.model.CartOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class CartKafkaConsumer {

    @Autowired
    private CartService cartService;

    @KafkaListener(topics = "cart_topic", groupId = "group_id")
    public void consume(CartOperation cartOperation) {
        String userId = cartOperation.getUserId();
        String productId = cartOperation.getProductId();
        int quantity = cartOperation.getQuantity();
        String operation = cartOperation.getOperation();

        if ("ADD".equalsIgnoreCase(operation)) {
            cartService.addItem(userId, productId, quantity);
        } else if ("REMOVE".equalsIgnoreCase(operation)) {
            cartService.removeItem(userId, productId, quantity);
        }
    }
}
