package com.trendyol.shoppingcart;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.enums.*;
import com.trendyol.shoppingcart.service.DeliveryCostService.DeliveryCostCalculatorServiceImpl;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartService;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartServiceImpl;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;

public class ShoppingCartApplication {

    // FIXED VARIABLES
    public static final double FIXED_COST = 2.99;
    public static final double COST_PER_PRODUCT = 2.0;
    public static final double COST_PER_DELIVERY = 1.0;

    // STATIC CATEGORY VARIABLES
    public static final String FOOD = CategoryStaticVariables.FOOD.getValue();
    public static final String CAR = CategoryStaticVariables.CAR.getValue();

    // STATIC PRODUCT VARIABLES
    public static final String APPLE = ProductStaticVariables.APPLE.getValue();
    public static final String BANANA = ProductStaticVariables.BANANA.getValue();
    public static final String ALMOND = ProductStaticVariables.ALMOND.getValue();
    public static final String GOLF = ProductStaticVariables.GOLF.getValue();

    // STATIC PRICE VARIABLES
    public static final Double D_FIVE = PriceStaticVariables.FIVE.getValue();
    public static final Double D_TEN = PriceStaticVariables.TEN.getValue();
    public static final Double D_ONEHUNDRED = PriceStaticVariables.ONEHUNDRED.getValue();

    // STATIC QUANTITY & AMOUNT VARIABLES
    public static final Integer INT_TEN = QuantityStaticVariables.TEN.getValue();
    public static final Integer INT_ONE = QuantityStaticVariables.ONE.getValue();
    public static final Integer INT_TWELVE = QuantityStaticVariables.TWELVE.getValue();
    public static final Integer INT_TWOHUNDRED = QuantityStaticVariables.TWOHUNDRED.getValue();


    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);

        // Create Category
        Category food = new Category(FOOD);
        Category car = new Category(CAR);

        // Create Product
        Product apple = new Product(APPLE, D_FIVE, food);
        Product almond = new Product(ALMOND, D_TEN, food);
        Product banana = new Product(BANANA, D_TEN, food);
        Product golf = new Product(GOLF, D_ONEHUNDRED, car);

        // Add products and quantities to ShoppingCart
        // If The same product added to shopping card , addItem method will be concat then update quantity.
        ShoppingCartService shoppingCart = new ShoppingCartServiceImpl(new DeliveryCostCalculatorServiceImpl(COST_PER_DELIVERY, COST_PER_PRODUCT, FIXED_COST));
        shoppingCart.addItem(apple, INT_TEN);
        shoppingCart.addItem(apple, INT_ONE);
        shoppingCart.addItem(almond, INT_ONE);
        shoppingCart.addItem(banana, INT_TWELVE);
        shoppingCart.addItem(golf, INT_ONE);

        // Define Campaign & Discounts
        Campaign blackFriday = new Campaign(food, D_TEN, INT_ONE, DiscountType.RATE);
        Campaign mothersDay = new Campaign(car, D_TEN, INT_ONE, DiscountType.AMOUNT);
        // Campaing Discounts are applied first then Coupons
        shoppingCart.applyDiscounts(Arrays.asList(blackFriday, mothersDay));


        // Define Coupon
        Coupon coupon = new Coupon(INT_TWOHUNDRED, D_TEN, DiscountType.RATE);
        shoppingCart.applyCoupon(coupon);

        // Calculate Result
        System.out.println(shoppingCart.print());
    }


}
