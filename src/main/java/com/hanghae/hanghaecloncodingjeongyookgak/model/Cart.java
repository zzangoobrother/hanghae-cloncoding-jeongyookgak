package com.hanghae.hanghaecloncodingjeongyookgak.model;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart extends Timestamped {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long cartCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Cart(Product product, Long cartCount) {

        this.product = product;
        this.cartCount = cartCount;

    }
}
