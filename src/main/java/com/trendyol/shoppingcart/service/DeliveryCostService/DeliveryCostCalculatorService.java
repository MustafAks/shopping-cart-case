package com.trendyol.shoppingcart.service.DeliveryCostService;

import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartService;

public interface DeliveryCostCalculatorService {

    Double calculateDeliveryCost(ShoppingCartService shoppingCartService);

}
