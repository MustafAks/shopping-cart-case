package com.trendyol.shoppingcart.repository;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;

import java.util.HashMap;
import java.util.List;

public interface ShoppingCartRepository {

    void addItem(Product product, Integer quantity);

    Double getTotalPriceOfAllProducts();

    Integer getProductSize();

    Integer calculateDistinctCategoryForDeliveries();

    Double getCampaignDiscountPrice();

    void applyCoupon(Coupon coupon);

    Double getCouponDiscount(Double totalAmount);

    HashMap<Product, Integer> getProducts();

    List<Campaign> getCampaigns();
}
