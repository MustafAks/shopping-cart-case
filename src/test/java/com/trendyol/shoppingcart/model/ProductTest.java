package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcart.common.ObjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    public static final double PRICE = 100.0;


    @Test
    public void product_test() {
        String categoryTitle = ObjectUtils.generateRandomString();
        Product product = new Product(ObjectUtils.generateRandomString(), PRICE, new Category(categoryTitle));
        assertEquals(product.getCategory().getTitle(), categoryTitle);
        assertEquals(product.getPrice(), PRICE);
    }


}
