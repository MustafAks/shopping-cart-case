package com.trendyol.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class ShoppingCardExceptionFactory {

    public static void throwException(HttpStatus httpStatus, String message) {
        if (httpStatus == null) {
            httpStatus = HttpStatus.EXPECTATION_FAILED;
        }

        throw new ShoppingCardException(httpStatus, message);
    }
}
