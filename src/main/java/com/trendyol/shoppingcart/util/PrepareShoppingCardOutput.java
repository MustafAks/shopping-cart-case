package com.trendyol.shoppingcart.util;


import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.dto.*;
import com.trendyol.shoppingcart.service.ShoppingCartService.ShoppingCartService;

public class PrepareShoppingCardOutput {
    public static ProductDto prepareCartData(Product product, ShoppingCartService cart) {
        ProductDto productDto = new ProductDto();
        productDto.setCategoryName(product.getCategory().getTitle());
        productDto.setProductName(product.getTitle());
        productDto.setQuantity(cart.calculateQuantityProduct(product));
        productDto.setUnitOfPrice(product.getPrice());
        productDto.setTotalPrice(product.getPrice() * cart.calculateQuantityProduct(product));
        return productDto;
    }
}
