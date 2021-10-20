package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CategoryImageRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.ProductRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.CategoryImage;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.model.ProductCategory;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CategoryImageRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryImageRepository categoryImageRepository;

    public ProductService(ProductRepository productRepository, CategoryImageRepository categoryImageRepository) {
        this.productRepository = productRepository;
        this.categoryImageRepository = categoryImageRepository;
    }

    public List<Product> home() {
        // todo 장바구니 큰순서대로 6개 조회
        return productRepository.findAllByCategory(ProductCategory.PORK);
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
