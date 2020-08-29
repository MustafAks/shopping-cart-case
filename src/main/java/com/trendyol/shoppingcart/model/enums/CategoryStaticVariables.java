package com.trendyol.shoppingcart.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryStaticVariables {

    FOOD("Food"),
    CAR("Car");

    private final String value;

    CategoryStaticVariables(String value) {
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
