package com.trendyol.shoppingcart.common;

import java.util.UUID;

public class ObjectUtils {


    public static String generateRandomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
