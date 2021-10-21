package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CategoryImageRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.ProductRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Cart;
import com.hanghae.hanghaecloncodingjeongyookgak.model.CategoryImage;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.model.ProductCategory;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CartRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CategoryImageRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryImageRepository categoryImageRepository;
    private final CartRepository cartRepository;

    public ProductService(ProductRepository productRepository, CategoryImageRepository categoryImageRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.categoryImageRepository = categoryImageRepository;
        this.cartRepository = cartRepository;
    }

    public List<Product> home() {
        List<Cart> carts = cartRepository.findAllByOrderByCartCountDesc();
        int size = 0;
        if (carts.size() > 0) {
            size = carts.size() >= 6 ? 6 : carts.size();
        }

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Cart cart = carts.get(i);

            Product product = productRepository.findById(cart.getProduct().getId()).orElseThrow(
                    () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
            );

            products.add(product);
        }

        return products;
    }

    public Map<String, Object> getCategoryProducts(String category) {
        ProductCategory productCategory = ProductCategory.categoryOf(category);
        List<Product> products = productRepository.findAllByCategory(productCategory);

        CategoryImage categoryImage = categoryImageRepository.findByProductCategory(productCategory);

        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("categoryImage", categoryImage.getCategoryImage());
        result.put("result", "success");

        return result;
    }

    public Map<String, Object> getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );

        Map<String, Object> result = new HashMap<>();
        result.put("id", String.valueOf(product.getId()));
        result.put("category", product.getCategory());
        result.put("title", product.getTitle());
        result.put("price", String.valueOf(product.getPrice()));
        result.put("image", product.getImage());
        result.put("imageDetail", product.getImageDetail());
        result.put("result", "success");

        return result;
    }

    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);
        return productRepository.save(product);
    }

    public CategoryImage createCategoryImage(CategoryImageRequestDto categoryImageRequestDto) {
        ProductCategory productCategory = ProductCategory.categoryOf(categoryImageRequestDto.getProductCategory());
        CategoryImage categoryImage = new CategoryImage(productCategory, categoryImageRequestDto.getCategoryImage());

        return categoryImageRepository.save(categoryImage);
    }
}
