package com.hanghae.hanghaecloncodingjeongyookgak.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String pw;

    @Column (nullable = false)
    private String nickname;

    @Column (nullable = true)
    private Long kakaoId;

    @OneToMany(mappedBy = "Cart")
    List<Cart> carts = new ArrayList<>();
}
