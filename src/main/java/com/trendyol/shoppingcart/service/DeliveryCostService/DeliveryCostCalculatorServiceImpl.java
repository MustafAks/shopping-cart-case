package com.trendyol.shoppingcart.service.DeliveryCostService;


import com.trendyol.shoppingcart.exception.ShoppingCardExceptionFactory;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartService;
import org.springframework.http.HttpStatus;

public class DeliveryCostCalculatorServiceImpl implements DeliveryCostCalculatorService {


    private Double costPerDelivery;
    private Double costPerProduct;
    private Double fixedCost;


    public DeliveryCostCalculatorServiceImpl(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    @Override
    public Double calculateDeliveryCost(ShoppingCartService shoppingCartService) {
        if (shoppingCartService == null) {
            ShoppingCardExceptionFactory.throwException(HttpStatus.BAD_REQUEST, "Cart is null");
        }
        // Number of deliveries is calculated by the number of distinct categories in the cart
        int numberOfDeliveries = shoppingCartService.calculateNumberOfDeliveries();

        // Number of produts are calculated
        int numberOfProducts = shoppingCartService.getProductSize();

        return calculateDeliveryCost(numberOfDeliveries, numberOfProducts);
    }

    private double calculateDeliveryCost(int numberOfDeliveries, int numberOfProducts) {
        return (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;
    }


}
