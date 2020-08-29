package com.trendyol.shoppingcart.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStaticVariables {

    APPLE("Apple"),
    ALMOND("Almond"),
    BANANA("Banana"),
    GOLF("Golf");

    private final String value;

    ProductStaticVariables(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

}
