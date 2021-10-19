package com.hanghae.hanghaecloncodingjeongyookgak.repository;

import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(String category);
}
