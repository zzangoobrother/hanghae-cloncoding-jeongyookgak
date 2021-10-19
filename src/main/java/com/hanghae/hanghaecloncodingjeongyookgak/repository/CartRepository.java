package com.hanghae.hanghaecloncodingjeongyookgak.repository;

import com.hanghae.hanghaecloncodingjeongyookgak.model.Cart;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Product> findAllByUsername(String username);
    Cart findByProductId(Long productId);
    void deleteByProductId(Long productId);
}
