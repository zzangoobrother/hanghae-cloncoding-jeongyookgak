package com.hanghae.hanghaecloncodingjeongyookgak.model;

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
    private String category;

    @Column
    private String title;

    @Column
    private Long price;

    @Column
    private String image;

    @Column
    private String imageDetail;

    public Product(String category, String title, Long price, String image, String imageDetail) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.image = image;
        this.imageDetail = imageDetail;
    }
}
