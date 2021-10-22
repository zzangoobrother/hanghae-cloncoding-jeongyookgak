package com.hanghae.hanghaecloncodingjeongyookgak.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartRequestDto {


    private Long productId;
    private Long count;


    public CartRequestDto(Long productId, Long count) {
        this.productId = productId;
        this.count = count;
    }

    public CartRequestDto(Long productId) {
        this.productId = productId;
    }
}