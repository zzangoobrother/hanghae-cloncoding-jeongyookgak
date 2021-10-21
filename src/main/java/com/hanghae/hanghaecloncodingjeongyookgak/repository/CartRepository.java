package com.hanghae.hanghaecloncodingjeongyookgak.repository;

import com.hanghae.hanghaecloncodingjeongyookgak.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserNickname(String username);
    Optional<Cart> findByProductId(Long productId);
    void deleteByProductId(Long productId);
    List<Cart> findAllByOrderByCartCountDesc();
}
