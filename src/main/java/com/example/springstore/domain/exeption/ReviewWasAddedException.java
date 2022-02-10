package com.example.springstore.domain.exeption;

public class ReviewWasAddedException extends RuntimeException {
    public ReviewWasAddedException(){
        super("Review was added for this order!");
    }
}
