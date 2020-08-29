package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcart.model.enums.DiscountType;

public class Coupon extends Discount {


    public Coupon(Integer minimumAmount, Double discountAmount, DiscountType discountType) {
        super(discountAmount, discountType, minimumAmount);
    }

    //If cart amount is less than minimum ,discount is not applied
    public boolean isAddForCart(Double totalCartPrice) {
        return totalCartPrice >= super.getMinimumCartAmount();

    }
}
