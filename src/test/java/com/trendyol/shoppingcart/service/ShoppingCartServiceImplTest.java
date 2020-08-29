package com.trendyol.shoppingcart.service;


import com.trendyol.shoppingcart.exception.ShoppingCardException;
import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.enums.*;
import com.trendyol.shoppingcart.repository.ShoppingCartRepository;
import com.trendyol.shoppingcart.service.DeliveryCostService.DeliveryCostCalculatorService;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ShoppingCartServiceImplTest {

    private DeliveryCostCalculatorService deliveryCostCalculatorService;
    private ShoppingCartServiceImpl shoppingCartService;
    private ShoppingCartRepository shoppingCartRepository;


    @BeforeEach
    public void prepare() {
        deliveryCostCalculatorService = mock(DeliveryCostCalculatorService.class);
        shoppingCartRepository = mock(ShoppingCartRepository.class);
        shoppingCartService = new ShoppingCartServiceImpl(deliveryCostCalculatorService);
    }

    @Test
    public void shoppingCartServiceImpl_addItem_success() {
        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(deliveryCostCalculatorService);
        Product apple = new Product(ProductStaticVariables.APPLE.getValue(), PriceStaticVariables.ONEHUNDRED.getValue()
                , new Category(CategoryStaticVariables.FOOD.getValue()));
        Product almonds = new Product(ProductStaticVariables.ALMOND.getValue()
                , PriceStaticVariables.ONEHUNDRED.getValue(), new Category(CategoryStaticVariables.FOOD.getValue()));

        shoppingCartService.addItem(apple, QuantityStaticVariables.TEN.getValue());
        shoppingCartService.addItem(almonds, QuantityStaticVariables.TEN.getValue());

        assertEquals(shoppingCartService.getProductSize(), 2);
        assertEquals(shoppingCartService.calculateQuantityProduct(apple), 10);
        assertEquals(shoppingCartService.calculateQuantityProduct(almonds), 10);
    }


    @Test
    public void shoppingCartServiceImpl_calculateTotalPriceAllProducts_success() {
        Category fruitCategory = new Category("Fruit");
        Category meatCategory = new Category("Meat");

        Product apple = new Product("Apple", 100.0, fruitCategory);
        Product parrot = new Product("Parrot", 50.0, meatCategory);

        shoppingCartService.addItem(apple, 5);
        shoppingCartService.addItem(parrot, 3);

        Campaign c1 = new Campaign(fruitCategory, 50, 5, DiscountType.RATE);
        Campaign c2 = new Campaign(meatCategory, 5, 3, DiscountType.AMOUNT);

        shoppingCartService.applyDiscounts(Arrays.asList(c1, c2));

        Double totalPrice = apple.getPrice() * 5 + parrot.getPrice() * 3;
        Double vegatablesDiscountExcepted = 5.0;
        Double fruitDiscountExcepted = totalPrice * 0.50;
        Double totalExpected = fruitDiscountExcepted + vegatablesDiscountExcepted;
        assertEquals(totalExpected, 330.0);
    }

    @Test
    public void applyCoupon_couponIsNull_ShouldThrowException() {
        Product apple = new Product("Apple", 100.0, new Category("Fruit"));
        Coupon coupon = null;
        shoppingCartService.addItem(apple, 1);
        Assertions.assertThrows(ShoppingCardException.class, () -> shoppingCartService.applyCoupon(coupon));
    }

    @Test
    public void calculateTotalAmountDiscounts_returns_zero() {
        assertEquals(0, shoppingCartService.calculateTotalAmountDiscounts());
    }


}
