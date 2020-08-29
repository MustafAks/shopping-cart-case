package com.trendyol.shoppingcart.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuantityStaticVariables {

    ONE(1),
    TEN(10),
    TWELVE(20),
    TWOHUNDRED(200);


    private final Integer value;

    QuantityStaticVariables(Integer value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @JsonValue
    public Integer getValue() {
        return value;
    }

}
