package com.trendyol.shoppingcart.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PriceStaticVariables {


    FIVE(5.0),
    TEN(10.0),
    ONEHUNDRED(100.0);


    private final Double value;

    PriceStaticVariables(Double value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @JsonValue
    public Double getValue() {
        return value;
    }

}
