package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.ProductRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 쇼핑하기 카테고리 조회
    @GetMapping("/api/list")
    public Map<String, Object> getCategoryProducts(@RequestParam String category) {
        return productService.getCategoryProducts(category);
    }

    // 상품 단건 조회
    @GetMapping("/api/detail")
    public Map<String, Object> getProduct(@RequestParam Long productId) {
        return productService.getProduct(productId);
    }

    // 상품 등록위해 구현
    @PostMapping("/api/product")
    public Product createProduct(@RequestBody ProductRequestDto ProductRequestDto) {
        return productService.createProduct(ProductRequestDto);
    }
}
