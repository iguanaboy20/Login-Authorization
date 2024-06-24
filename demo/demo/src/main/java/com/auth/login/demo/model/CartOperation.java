package com.auth.login.demo.model;

public class CartOperation {

    private String userId;
    private String productId;
    private int quantity;
    private String operation;

    public CartOperation() {}

    public CartOperation(String userId, String productId, int quantity, String operation) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.operation = operation;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
