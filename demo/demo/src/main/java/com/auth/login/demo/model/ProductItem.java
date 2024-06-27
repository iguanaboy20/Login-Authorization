package com.auth.login.demo.model;


import jakarta.persistence.*;

@Entity
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer productId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public ProductItem() {}

    public ProductItem(Integer productId, int quantity, Cart cart) {
        this.productId = productId;
        this.quantity = quantity;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
