package com.hanghae.hanghaecloncodingjeongyookgak.model;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;

    @Column
    private String title;

    @Column
    private Long price;

    @Column
    private String image;

    @Column
    private String imageDetail;

    public Product(ProductCategory category, String title, Long price, String image, String imageDetail) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.image = image;
        this.imageDetail = imageDetail;
    }

    public Product(ProductRequestDto productRequestDto) {
        this.category = ProductCategory.categoryOf(productRequestDto.getCategory());
        this.title = productRequestDto.getTitle();
        this.price = productRequestDto.getPrice();
        this.image = productRequestDto.getImage();
        this.imageDetail = productRequestDto.getImageDetail();
    }
}
