package com.trendyol.shoppingcart.service.ShoppingCartService;


import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;

import java.util.List;

public interface ShoppingCartService {

    /**
     * This method add products and quantity to shoppingCard
     */
    void addItem(Product product, int quantity);

    /**
     * This method shows the output
     */
    String print();

    /**
     * Provides the discount made on the category
     * Rules : If bought more than 3 items discount will be %20
     */
    void applyDiscounts(List<Campaign> campaigns);

    /**
     * Calculate coupon
     */
    void applyCoupon(Coupon coupon);


    /**
     * Calculate Total Amount Discounts
     */


    Double calculateTotalAmountDiscounts();

    /**
     * Calculate Total Price ALL Products
     */

    Double calculateTotalPriceAllProducts();

    /**
     * Get All product
     */
    Integer getProductSize();

    /**
     * Calculate Quantity Product
     */
    Integer calculateQuantityProduct(Product product);


    /**
     * Add Campaigns To Cart
     */
    void addCampaignsToCart(List<Campaign> asList);

    /**
     * Calculate Disctinct Deliveries In Shopping Cart
     */
    Integer calculateNumberOfDeliveries();
}
