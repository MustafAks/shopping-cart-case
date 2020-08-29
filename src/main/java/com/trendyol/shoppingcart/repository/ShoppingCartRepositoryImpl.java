package com.trendyol.shoppingcart.repository;


import com.trendyol.shoppingcart.exception.ShoppingCardExceptionFactory;
import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    private HashMap<Product, Integer> products;
    private List<Campaign> campaigns;
    private Coupon coupon;

    public ShoppingCartRepositoryImpl() {
        this.products = new HashMap<>();
        this.campaigns = new ArrayList<>();
    }

    private HashMap<Product, Integer> getProductsByCategory(Category category) {
        HashMap<Product, Integer> resultProducts = new HashMap<>();

        for (Product p : products.keySet()) {
            if (p.getCategory().getTitle().equals(category.getTitle())) {
                resultProducts.put(p, products.get(p));
            }
        }
        return resultProducts;
    }


    private Integer getAllProductQuantityCountByCategory(Category category) {
        HashMap<Product, Integer> productsByCategory = getProductsByCategory(category);
        // Initial value is 0 (identity) for products
        return productsByCategory.values().stream().reduce(0, Integer::sum);
    }

    private Double applyCampaign() {
        Double resultDiscountPrice = 0.0;
        Double tempDiscountPrice;

        for (Campaign campaign : campaigns) {
            HashMap<Product, Integer> productsByCategory = getProductsByCategory(campaign.getCategory());
            if ((Integer) productsByCategory.values().stream().mapToInt(Integer::intValue).sum() >= campaign.getMinimumCartAmount()) {
                switch (campaign.getDiscountType()) {
                    case RATE:
                        tempDiscountPrice = applyDiscountTypeRateCampaign(campaign, productsByCategory.size());
                        if (tempDiscountPrice > resultDiscountPrice){
                            resultDiscountPrice = tempDiscountPrice;
                        }
                        break;
                    case AMOUNT:
                        tempDiscountPrice = applyDiscountTypeAmountCampaign(campaign);
                        resultDiscountPrice += tempDiscountPrice;
                        break;
                    default:
                        ShoppingCardExceptionFactory.throwException(HttpStatus.EXPECTATION_FAILED, "Campaign must be Rate Or Amount");

                }
            }
        }

        return resultDiscountPrice;
    }

    private Double applyDiscountTypeAmountCampaign(Campaign campaign) {
        Category campaignCategory = campaign.getCategory();
        Double discountAmount = campaign.getDiscountAmount();

        if (getAllProductQuantityCountByCategory(campaignCategory) >= campaign.getMinimumCartAmount()) {
            return discountAmount;
        } else {
            return 0.0;
        }
    }

    private Double applyDiscountTypeRateCampaign(Campaign campaign, Integer discountRule) {
        Category campaignCategory = campaign.getCategory();
        Double discountRate = campaign.getDiscountAmount();

        if (getAllProductQuantityCountByCategory(campaignCategory) >= campaign.getMinimumCartAmount()) {
            Double totalPriceOfAllProducts = getTotalPriceOfAllProducts();
            switch (discountRule) {
                case 3:
                    return (totalPriceOfAllProducts * 20) / 100;
                case 5:
                    return (totalPriceOfAllProducts * 50 )/ 100.0;
                default:
                   return  (totalPriceOfAllProducts * discountRate) / 100.0;
            }
        } else {
            return 0.0;
        }

    }


    public void addItem(Product product, Integer quantity) {
        if (product != null && quantity != null && quantity > 0) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product key = entry.getKey();
                Integer value = entry.getValue();
                if (key.getTitle().equals(product.getTitle())) {
                    value += quantity;
                    entry.setValue(value);
                    return;
                }
            }
            products.put(product, quantity);
        }
    }


    public Double getTotalPriceOfAllProducts() {
        double totalPrice = 0.0;

        for (Product product : products.keySet()) {
            Integer quantityOfProduct = products.get(product);
            Double priceOfProduct = product.getPrice();
            totalPrice += quantityOfProduct * priceOfProduct;
        }
        return totalPrice;
    }

    public Integer getProductSize() {
        return products.size();
    }

    public Integer getQuantityOfProduct(Product product) {
        if (!products.containsKey(product)) {
            ShoppingCardExceptionFactory.throwException(HttpStatus.EXPECTATION_FAILED, "There is no product");
            return null;
        }
        return products.get(product);
    }

    public Integer calculateDistinctCategoryForDeliveries() {
        return calculateDisctintCategoriesInCart();
    }

    private Integer calculateDisctintCategoriesInCart() {
        return products.keySet().stream().collect(Collectors.groupingBy(p -> p.getCategory().getTitle())).size();
    }

    public Double getCampaignDiscountPrice() {
        return applyCampaign();
    }

    public void applyCoupon(Coupon coupon) {
        if (coupon == null) {
            ShoppingCardExceptionFactory.throwException(HttpStatus.EXPECTATION_FAILED, "Coupon must not be null");
        }

        //If cart amount is less than minimum ,discount is not applied
        if (!coupon.isAddForCart(getTotalPriceOfAllProducts())) {
            ShoppingCardExceptionFactory.throwException(HttpStatus.EXPECTATION_FAILED, "Coupon is not applicable for this cart");
        }
        this.coupon = coupon;
    }

    public Double getCouponDiscount(Double totalAmount) {
        double resultCouponDiscount = 0.0;

        if (coupon != null && coupon.isAddForCart(getTotalPriceOfAllProducts())) {
            switch (coupon.getDiscountType()) {
                case RATE:
                    resultCouponDiscount = (totalAmount * coupon.getDiscountAmount()) / 100.0;
                    break;
                case AMOUNT:
                    return coupon.getDiscountAmount();
                default:
                    ShoppingCardExceptionFactory.throwException(HttpStatus.EXPECTATION_FAILED, "Discount Rate must be Rate Or Amount");
                    break;
            }
        }

        return resultCouponDiscount;
    }


    public HashMap<Product, Integer> getProducts() {
        return products;
    }


    public List<Campaign> getCampaigns() {
        return campaigns;
    }

}
