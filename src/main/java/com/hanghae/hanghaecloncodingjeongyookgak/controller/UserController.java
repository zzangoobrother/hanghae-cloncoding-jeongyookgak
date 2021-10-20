package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.SignUpRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.UserRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.User;
import com.hanghae.hanghaecloncodingjeongyookgak.security.UserDetailsImpl;
import com.hanghae.hanghaecloncodingjeongyookgak.security.jwt.JwtTokenProvider;
import com.hanghae.hanghaecloncodingjeongyookgak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //가입 요청
    @PostMapping("/api/signup")
    public Map<String, String> registerUser(@RequestBody SignUpRequestDto requestDto) throws HanghaeClonException{
        User user = userService.registerUser(requestDto);
        Map<String, String> result = new HashMap<>();
            result.put("result", "successs");
            return result;
    }

    //로그인
    @PostMapping("/api/login")
    public Map<String,String> login(@RequestBody UserRequestDto requestDto) throws HanghaeClonException{
        User user = userService.login(requestDto);

        Map<String,String> result = new HashMap<>();
        result.put("token",jwtTokenProvider.createToken(user.getEmail(), user.getEmail(),user.getNickname()));
        result.put("email", user.getEmail());
        result.put("nickname",user.getNickname());
        result.put("result", "success");

        return result;
    }
}