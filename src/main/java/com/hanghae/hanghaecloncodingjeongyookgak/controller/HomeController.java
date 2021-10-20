package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public Map<String, Object> home() {
        List<Product> products = productService.home();

        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("result", "success");
        return result;
    }
}
