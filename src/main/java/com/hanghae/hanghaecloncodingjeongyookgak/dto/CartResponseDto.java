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

    public CartResponseDto(Long id, String title, Long price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }
}
