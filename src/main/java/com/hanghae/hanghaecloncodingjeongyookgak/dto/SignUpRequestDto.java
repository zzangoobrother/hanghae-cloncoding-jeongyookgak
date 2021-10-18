package com.hanghae.hanghaecloncodingjeongyookgak.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String pw;
    private String pwCheck;
    private String nickname;
}
