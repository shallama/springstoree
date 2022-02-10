package com.example.springstore.domain.exeption;

public class OrderCanNotFindException extends RuntimeException {
    public OrderCanNotFindException(){
        super("Order not found!");
    }
}
