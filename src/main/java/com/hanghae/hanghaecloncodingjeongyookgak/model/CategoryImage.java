package com.hanghae.hanghaecloncodingjeongyookgak.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CategoryImage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductCategory productCategory;

    @Column(nullable = false)
    private String categoryImage;

    public CategoryImage(ProductCategory productCategory, String categoryImage) {
        this.productCategory = productCategory;
        this.categoryImage = categoryImage;
    }
}
