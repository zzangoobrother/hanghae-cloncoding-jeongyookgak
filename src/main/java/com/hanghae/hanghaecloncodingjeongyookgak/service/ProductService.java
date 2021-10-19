package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> home() {
        return productRepository.findAllByCategory("PORK");
    }

    public Map<String, Object> getCategoryProducts(String category) {
        List<Product> products = productRepository.findAllByCategory(category);
        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("result", "success");

        return result;
    }

    public Map<String, String> getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );

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

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
