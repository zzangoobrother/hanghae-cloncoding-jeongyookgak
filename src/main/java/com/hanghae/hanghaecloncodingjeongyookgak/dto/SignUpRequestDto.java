package com.hanghae.hanghaecloncodingjeongyookgak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String pw;
    private String pwCheck;
    private String nickname;
}
