package com.hanghae.hanghaecloncodingjeongyookgak.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {
    public CartResponseDto() {
    }

    private Long id;
    private String title;
    private Long price;
    private String image;
    private Long count;
    Long sumPrice;

    public CartResponseDto(Long id, String title, Long price, String image, Long count, Long sumPrice) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.count = count;
        this.sumPrice = sumPrice;
    }
}
