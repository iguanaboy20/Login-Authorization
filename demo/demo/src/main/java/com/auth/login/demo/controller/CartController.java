package com.auth.login.demo.controller;

import com.auth.login.demo.model.Cart;
import com.auth.login.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Void> addItem(@PathVariable Long userId, @RequestParam Integer productId, @RequestParam int quantity) {
        cartService.addItem(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<Void> removeItem(@PathVariable Long userId, @RequestParam Integer productId, @RequestParam int quantity) {
        cartService.removeItem(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }
}
