package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        List<Product> products = productService.getCategoryProducts(category);

        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("result", "success");

        return result;
    }

    // 상품 단건 조회
    @GetMapping("/api/detail")
    public Map<String, String> getProduct(@RequestParam Long productId) {
        Product product = productService.getProduct(productId);

        Map<String, String> result = new HashMap<>();
        result.put("id", String.valueOf(product.getId()));
        result.put("category", product.getCategory());
        result.put("title", product.getTitle());
        result.put("price", String.valueOf(product.getPrice()));
        result.put("image", product.getImage());
        result.put("imageDetail", product.getImageDetail());
        result.put("result", "success");

        return result;
    }

    // 상품 등록위해 구현
    @PostMapping("/api/product")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
