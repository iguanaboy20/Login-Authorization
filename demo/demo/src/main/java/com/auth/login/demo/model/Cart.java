package com.auth.login.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Cart{

    @Setter
    @Id
    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductItem> items = new HashSet<>();

    public Cart() {}

    public Cart(Long userId) {
        this.userId = userId;
    }

    public void addItem(ProductItem item) {
        items.add(item);
    }

    public void removeItem(ProductItem item) {
        items.remove(item);
    }
}
