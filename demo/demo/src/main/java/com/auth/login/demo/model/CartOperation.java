package com.auth.login.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CartOperation {

    private Long userId;
    private Integer productId;
    private int quantity;
    private String operation;

    public CartOperation() {}

    public CartOperation(Long userId, Integer productId, int quantity, String operation) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.operation = operation;
    }

}
