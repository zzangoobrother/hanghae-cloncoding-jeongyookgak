package com.hanghae.hanghaecloncodingjeongyookgak.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {
    public CartResponseDto() {
    }

    private Long id;
    private Long productId;
    private String title;
    private Long price;
    private String image;
    private Long count;
    Long sumPrice;

    public CartResponseDto(Long id,Long productId, String title, Long price, String image, Long count, Long sumPrice) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.image = image;
        this.count = count;
        this.sumPrice = sumPrice;
    }
}
