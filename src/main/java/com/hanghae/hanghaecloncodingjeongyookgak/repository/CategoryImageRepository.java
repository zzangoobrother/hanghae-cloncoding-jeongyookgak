package com.hanghae.hanghaecloncodingjeongyookgak.repository;

import com.hanghae.hanghaecloncodingjeongyookgak.model.CategoryImage;
import com.hanghae.hanghaecloncodingjeongyookgak.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long> {
    CategoryImage findByProductCategory(ProductCategory productCategory);
}
