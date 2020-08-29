package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcart.model.enums.DiscountType;

public class Discount {

    private Double discountAmount;
    private DiscountType discountType;
    private Integer minimumCartAmount;

    public Discount(Double discountAmount, DiscountType discountType, Integer minimumCartAmount) {
        this.discountAmount = discountAmount;
        this.discountType = discountType;
        this.minimumCartAmount = minimumCartAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }


    public DiscountType getDiscountType() {
        return discountType;
    }


    public Integer getMinimumCartAmount() {
        return minimumCartAmount;
    }

}
