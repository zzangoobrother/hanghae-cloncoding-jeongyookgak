package com.hanghae.hanghaecloncodingjeongyookgak.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.SignUpRequestDto;
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

    @Column(nullable = false)
    private String email;

    @Column (nullable = false)
    private String pw;

    @Column (nullable = false)
    private String nickname;

    @Column (nullable = true)
    private Long kakaoId;


    public User(String email, String pw, String nickname, Long kakaoId){
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.kakaoId = kakaoId;
    }

    public User(String email, String pw, String nickname){
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
    }

    public User(SignUpRequestDto signUpRequestDto){
        this.email = signUpRequestDto.getEmail();
        this.pw = signUpRequestDto.getPw();
        this.nickname = signUpRequestDto.getNickname();
        this.kakaoId = null;
    }
}
