package com.auth.login.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cart_id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductItem> items = new ArrayList<>();

    public Cart(String userId) {
    }

    public void addItem(ProductItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(ProductItem item) {
        items.remove(item);
        item.setCart(null);
    }
}
