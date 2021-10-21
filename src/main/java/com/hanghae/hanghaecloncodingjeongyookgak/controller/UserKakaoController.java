package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.User;
import com.hanghae.hanghaecloncodingjeongyookgak.security.jwt.JwtTokenProvider;
import com.hanghae.hanghaecloncodingjeongyookgak.service.KakaoUserService;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public class UserKakaoController {
    private final KakaoUserService kakaoUserService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserKakaoController(KakaoUserService kakaoUserService, JwtTokenProvider jwtTokenProvider){
        this.kakaoUserService = kakaoUserService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @Getter("/kakao/callback")
//    public Map<String,String> kakaoLogin(@RequestParam String code) throws JsonProcessingException{
//        User user = kakaoUserService.kakaoLogin(code);
//
//        Map<String,String> result = new HashMap<>();
//        result.put("thken",jwtTokenProvider.createToken(user.getEmail(), user.getEmail(), user.getNickname()));
//        result.put("email", user.getEmail());
//        result.put("nickname", user.getNickname());
//        result.put("result", "success");
//
//        return result;
//    }
}
