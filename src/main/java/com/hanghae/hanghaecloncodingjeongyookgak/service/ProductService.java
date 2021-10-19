package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> home() {
        return productRepository.findAllByCategory("PORK");
    }

    public List<Product> getCategoryProducts(String category) {
        return productRepository.findAllByCategory(category);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );
    }
}
