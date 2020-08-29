package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcart.model.enums.DiscountType;

public class Campaign extends Discount {

    private final Category category;

    public Campaign(Category category, double discountAmount, int minimumAmount, DiscountType rate) {
        super(discountAmount, rate, minimumAmount);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}
