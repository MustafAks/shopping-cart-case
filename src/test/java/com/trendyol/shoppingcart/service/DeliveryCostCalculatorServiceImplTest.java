package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.exception.ShoppingCardException;
import com.trendyol.shoppingcart.service.DeliveryCostService.DeliveryCostCalculatorServiceImpl;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryCostCalculatorServiceImplTest {

    private DeliveryCostCalculatorServiceImpl deliveryCostCalculatorService;
    private ShoppingCartService shoppingCartService;

    Double costPerDelivery = 1.0;
    Double costPerProduct = 2.0;
    Double fixedCost = 2.99;

    @BeforeEach
    public void prepare() {
        shoppingCartService = Mockito.mock(ShoppingCartService.class);
    }


    @Test
    public void constructor_deliveryCostCalculatorService() {
        deliveryCostCalculatorService = new DeliveryCostCalculatorServiceImpl(costPerDelivery, costPerProduct, fixedCost);
    }

    @Test
    public void calculateDeliveryCost_success() {
        deliveryCostCalculatorService = new DeliveryCostCalculatorServiceImpl(costPerDelivery, costPerProduct, fixedCost);
        deliveryCostCalculatorService.calculateDeliveryCost(shoppingCartService);

    }

    @Test
    public void calculateDeliveryCost_error() {
        deliveryCostCalculatorService = new DeliveryCostCalculatorServiceImpl(costPerDelivery, costPerProduct, fixedCost);
        assertThrows(ShoppingCardException.class, () -> deliveryCostCalculatorService.calculateDeliveryCost(null));
    }

}
