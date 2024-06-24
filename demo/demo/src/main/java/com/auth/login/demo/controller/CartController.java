package com.auth.login.demo.controller;

import com.auth.login.demo.model.Cart;
import com.auth.login.demo.service.CartKafkaProducer;
import com.auth.login.demo.service.CartService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartKafkaProducer kafkaProducer;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestParam String userId, @RequestParam String productId, @RequestParam int quantity) {
        kafkaProducer.sendMessage(userId, productId, quantity, "ADD");
        return new ResponseEntity<>("Item added to cart", HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestParam String userId, @RequestParam String productId, @RequestParam int quantity) {
        kafkaProducer.sendMessage(userId, productId, quantity, "REMOVE");
        return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
    }
}
