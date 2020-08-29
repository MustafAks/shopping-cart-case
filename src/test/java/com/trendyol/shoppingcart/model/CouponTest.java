package com.trendyol.shoppingcart.model;

import org.junit.jupiter.api.Test;

import static com.trendyol.shoppingcart.model.enums.DiscountType.RATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponTest {

    public static final double DISCOUNT_AMOUNT_10 = 10.0;
    public static final int MINIMUM_AMOUNT_100 = 100;


    @Test
    public void coupon_test() {
        Coupon coupon = new Coupon(MINIMUM_AMOUNT_100, DISCOUNT_AMOUNT_10, RATE);
        assertEquals(coupon.getMinimumCartAmount(), MINIMUM_AMOUNT_100);
        assertEquals(coupon.getDiscountAmount(), DISCOUNT_AMOUNT_10);
        assertEquals(coupon.getDiscountType(), RATE);
    }

    @Test
    public void coupon_test_isAddForCart() {
        Coupon coupon = new Coupon(MINIMUM_AMOUNT_100, DISCOUNT_AMOUNT_10, RATE);
        coupon.isAddForCart(100.0);
        assertEquals(coupon.getMinimumCartAmount(), MINIMUM_AMOUNT_100);

    }


}
