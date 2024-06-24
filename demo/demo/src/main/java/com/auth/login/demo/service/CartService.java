package com.auth.login.demo.service;

import com.auth.login.demo.model.Cart;
import com.auth.login.demo.model.ProductItem;
import com.auth.login.demo.repo.CartRepository;
import com.auth.login.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productItemRepository;

    public Cart getCart(String userId) {
        return cartRepository.findById(userId).orElse(new Cart(userId));
    }

    public void addItem(String userId, String productId, int quantity) {
        Cart cart = cartRepository.findById(userId).orElse(new Cart(userId));
        Optional<ProductItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            cart.addItem(new ProductItem(productId, quantity, cart));
        }
        cartRepository.save(cart);
    }

    public void removeItem(String userId, String productId, int quantity) {
        Cart cart = cartRepository.findById(userId).orElse(null);
        if (cart != null) {
            Optional<ProductItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst();
            if (existingItem.isPresent()) {
                ProductItem item = existingItem.get();
                if (item.getQuantity() <= quantity) {
                    cart.removeItem(item);
                    productItemRepository.delete(item);
                } else {
                    item.setQuantity(item.getQuantity() - quantity);
                }
                cartRepository.save(cart);
            }
        }
    }
}
