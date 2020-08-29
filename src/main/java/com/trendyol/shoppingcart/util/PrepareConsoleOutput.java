package com.trendyol.shoppingcart.util;

import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.dto.*;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartServiceImpl;

import java.util.List;
import java.util.Map;

import static com.trendyol.shoppingcart.util.PrepareShoppingCardOutput.prepareCartData;

public class PrepareConsoleOutput {

    public static String createTableForResult(StringBuilder sb, Map<String, List<Product>> productsTitle
            , Double totalPriceOfProducts, Double deliveryCost, ShoppingCartServiceImpl shoppingCartService, Double totalAmountAfterDiscounts) {

        Double totalDiscount = totalPriceOfProducts - totalAmountAfterDiscounts;
        Double totalPayment = totalAmountAfterDiscounts - deliveryCost;


        sb.append("\n");
        sb.append("================================" + "Shopping Cart" + "===================================\n");
        sb.append("\n");
        sb.append(String.format("%15s %15s %15s %15s %15s%n", "Category", "Product", "Quantity", "Price", "Total Price"));
        for (String category : productsTitle.keySet()) {
            for (Product product : productsTitle.get(category)) {
                ProductDto productDto = prepareCartData(product, shoppingCartService);
                sb.append(productDto.toString());
            }
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("===================================" + "Invoice" + "======================================\n");
        sb.append("\n");
        sb.append(String.format("Total Price: %.2f%n", totalPriceOfProducts.floatValue()));
        sb.append(String.format("Total Discount:" + totalDiscount.floatValue() + "\n"));
        sb.append(String.format("Delivery Cost: %.2f%n", deliveryCost));
        sb.append(String.format("Total Payment: %.2f%n", totalPayment));
        sb.append("\n");

        return sb.toString();
    }


}
