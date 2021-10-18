package com.hanghae.hanghaecloncodingjeongyookgak.model;

<<<<<<< HEAD
public class User {
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
>>>>>>> 5245e511476222722d13e1cd47c71728ed48d14c
}
