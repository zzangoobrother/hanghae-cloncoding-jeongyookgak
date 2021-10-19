package com.hanghae.hanghaecloncodingjeongyookgak.model;

import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ProductCategory {
    PORK,
    BEEF,
    CHICKEN,
    SEAFOOD,
    MEALKIT,
    MILK,
    EGG,
    BABY;

//    private String category;
//
//    ProductCategory(String category) {
//        this.category = category;
//    }
//
//    public static ProductCategory findCategory(String category) {
//        return Arrays.stream(ProductCategory.values())
//                .filter(v -> v.category.equals(category))
//                .findFirst()
//                .orElseThrow(() -> new HanghaeClonException())
//    }
}
