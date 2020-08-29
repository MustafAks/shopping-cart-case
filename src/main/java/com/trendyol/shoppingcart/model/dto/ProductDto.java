package com.trendyol.shoppingcart.model.dto;


public class ProductDto {
    private String productName;
    private Integer quantity;
    private String categoryName;
    private Double totalPrice;
    private Double unitOfPrice;


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setUnitOfPrice(Double unitOfPrice) {
        this.unitOfPrice = unitOfPrice;
    }

    @Override
    public String toString() {
        return String.format("%15s %15s %15d %15.2f %15.2f%n", categoryName, productName, quantity, unitOfPrice, totalPrice);
    }
}
