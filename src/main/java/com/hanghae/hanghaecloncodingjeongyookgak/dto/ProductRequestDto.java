package com.hanghae.hanghaecloncodingjeongyookgak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String category;
    private String title;
    private Long price;
    private String image;
    private String imageDetail;
}
