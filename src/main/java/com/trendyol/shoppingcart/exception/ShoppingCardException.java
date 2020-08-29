package com.trendyol.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class ShoppingCardException extends RuntimeException {
    public HttpStatus httpStatus;
    public String message;

    public ShoppingCardException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
