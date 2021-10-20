package com.hanghae.hanghaecloncodingjeongyookgak.model;

public enum ProductCategory {
    PORK("pork"),
    BEEF("beef"),
    CHICKEN("chicken"),
    SEAFOOD("seafood"),
    MEALKIT("mealkit"),
    MILK("milk"),
    EGG("egg"),
    BABY("baby");

    private final String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public static ProductCategory categoryOf(String category) {
        for (ProductCategory productCategory : ProductCategory.values()) {
            if (category.equalsIgnoreCase(productCategory.toString())) {
                return productCategory;
            }
        }

        throw new IllegalArgumentException("올바른 카테고리가 아닙니다.");
    }
}
