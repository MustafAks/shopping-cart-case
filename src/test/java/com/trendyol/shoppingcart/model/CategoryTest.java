package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcart.common.ObjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTest {

    @Test
    public void category_test() {
        String title = ObjectUtils.generateRandomString();
        Category category = new Category(title);
        assertNotNull(category.getTitle());
    }


}
