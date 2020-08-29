package com.trendyol.shoppingcart.model;

import org.junit.jupiter.api.Test;

import static com.trendyol.shoppingcart.model.enums.DiscountType.RATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    public static final double DISCOUNT_AMOUNT_10 = 10.0;
    public static final int MINIMUM_AMOUNT_100 = 100;


    @Test
    public void discount_test() {
        Discount discount = new Discount(DISCOUNT_AMOUNT_10, RATE, MINIMUM_AMOUNT_100);
        assertEquals(discount.getMinimumCartAmount(), MINIMUM_AMOUNT_100);
        assertEquals(discount.getDiscountAmount(), DISCOUNT_AMOUNT_10);
        assertEquals(discount.getDiscountType(), RATE);
    }




}
