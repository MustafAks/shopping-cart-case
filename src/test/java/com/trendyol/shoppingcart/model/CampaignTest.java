package com.trendyol.shoppingcart.model;

import org.junit.jupiter.api.Test;

import static com.trendyol.shoppingcart.model.enums.CategoryStaticVariables.FOOD;
import static com.trendyol.shoppingcart.model.enums.DiscountType.RATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CampaignTest {

    public static final double DISCOUNT_AMOUNT_20 = 20.0;
    public static final int MINIMUM_AMOUNT_3 = 3;

    @Test
    void campaign_test() {

        Campaign campaign = new Campaign(new Category(FOOD.getValue()), DISCOUNT_AMOUNT_20, MINIMUM_AMOUNT_3, RATE);
        assertNotNull(campaign.getCategory());
        assertEquals(campaign.getMinimumCartAmount(), MINIMUM_AMOUNT_3);
        assertEquals(campaign.getDiscountAmount(), DISCOUNT_AMOUNT_20);
        assertEquals(campaign.getDiscountType(), RATE.valueOf("RATE"));
    }


}
