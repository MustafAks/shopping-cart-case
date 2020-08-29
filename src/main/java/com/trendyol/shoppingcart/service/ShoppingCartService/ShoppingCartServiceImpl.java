package com.trendyol.shoppingcart.service.ShoppingCartService;


import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.repository.ShoppingCartRepositoryImpl;
import com.trendyol.shoppingcart.service.DeliveryCostService.DeliveryCostCalculatorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.trendyol.shoppingcart.util.PrepareConsoleOutput.createTableForResult;


public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ShoppingCartRepositoryImpl shoppingCartRepositoryImpl = new ShoppingCartRepositoryImpl();
    private DeliveryCostCalculatorService deliveryCostCalculator;

    public ShoppingCartServiceImpl(DeliveryCostCalculatorService deliveryCostCalculator) {
        this.deliveryCostCalculator = deliveryCostCalculator;
    }


    @Override
    public Double calculateTotalPriceAllProducts() {
        return shoppingCartRepositoryImpl.getTotalPriceOfAllProducts();
    }

    @Override
    public Integer getProductSize() {
        return shoppingCartRepositoryImpl.getProductSize();
    }

    @Override
    public Integer calculateQuantityProduct(Product product) {
        return shoppingCartRepositoryImpl.getQuantityOfProduct(product);
    }

    @Override
    public void addCampaignsToCart(List<Campaign> campaigns) {
        shoppingCartRepositoryImpl.getCampaigns().addAll(campaigns);
    }


    @Override
    public void applyDiscounts(List<Campaign> campaigns) {
        this.addCampaignsToCart(campaigns);

    }

    public Integer calculateNumberOfDeliveries() {
        return shoppingCartRepositoryImpl.calculateDistinctCategoryForDeliveries();
    }

    @Override
    public void addItem(Product product, int quantity) {
        if (quantity > 0) {
            shoppingCartRepositoryImpl.addItem(product, quantity);
        }
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        shoppingCartRepositoryImpl.applyCoupon(coupon);
    }

    @Override
    public Double calculateTotalAmountDiscounts() {
        Double totalAmount = calculateTotalPriceAllProducts();
        totalAmount -= shoppingCartRepositoryImpl.getCampaignDiscountPrice();
        totalAmount -= shoppingCartRepositoryImpl.getCouponDiscount(totalAmount);
        return totalAmount;
    }


    public String print() {
        StringBuilder sb = new StringBuilder();
        HashMap<Product, Integer> products = shoppingCartRepositoryImpl.getProducts();
        Map<String, List<Product>> productsTitle = products.keySet().stream().collect(Collectors.groupingBy(p -> p.getCategory().getTitle()));
        return createTableForResult(sb, productsTitle, calculateTotalPriceAllProducts(), deliveryCostCalculator.calculateDeliveryCost(this),
                this, calculateTotalAmountDiscounts());
    }


}
