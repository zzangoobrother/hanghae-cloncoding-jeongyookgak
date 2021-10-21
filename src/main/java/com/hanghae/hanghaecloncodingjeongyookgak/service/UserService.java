package com.hanghae.hanghaecloncodingjeongyookgak.service;


import com.hanghae.hanghaecloncodingjeongyookgak.dto.SignUpRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.UserRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.User;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User registerUser(SignUpRequestDto requestDto) throws HanghaeClonException {

        // PW 암호화
        String pw = passwordEncoder.encode(requestDto.getPw());

        //email 중복체크
        String email = requestDto.getEmail();
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new HanghaeClonException(ErrorCode.EMAIL_DUPLICATE);
        }

        //nickname 중복체크
        String nickname = requestDto.getNickname();
        Optional<User> found2 = userRepository.findByNickname(nickname);
        if (found2.isPresent()) {
            throw new HanghaeClonException((ErrorCode.NICKNAME_DUPLICATE));
        }

        //비밀번호 == 비밀번호 체크
        String password = requestDto.getPw();
        String passwordCheck = requestDto.getPwCheck();
        //패스워드 8자 이상 20자 이하
        if (!password.isEmpty() && !passwordCheck.isEmpty()) {
            if (password.length() >= 8 && password.length() <= 20) {
                if (!password.equals(passwordCheck)) {
                    throw new HanghaeClonException(ErrorCode.USER_NOT_FOUND);
                }
            }
        }
        User user = new User(email, pw, nickname);
        return userRepository.save(user);
    }


        // 로그인
        public User login (UserRequestDto requestDto) throws HanghaeClonException {
            User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                    () -> new HanghaeClonException(ErrorCode.USER_NOT_FOUND)
            );

            //패스워드 암호화
            if (!passwordEncoder.matches(requestDto.getPw(), user.getPw())) {
                throw new HanghaeClonException(ErrorCode.USER_NOT_FOUND);
            }
            return user;
        }


        //email 중복
        public Map<String, String> sameId (UserRequestDto userRequstDto){
            User user = userRepository.findByEmail(userRequstDto.getEmail()).orElseThrow(null);

            Map<String, String> result = new HashMap<>();
            if (user == null) {
                result.put("result", "success");
                return result;
            }
            result.put("result", "fail");
            result.put("message", "중복된 email이 있습니다.");
            return result;
        }


        //닉네임 중복
        public Map<String, String> sameNickname (SignUpRequestDto signUpRequestDto){
            User user = userRepository.findByNickname(signUpRequestDto.getNickname()).orElseThrow(null);
            Map<String, String> result = new HashMap<>();
            if (user == null) {
                result.put("result", "success");
                return result;
            }

            result.put("result", "fail");
            result.put("message", "중복된 nickname이 있습니다.");
            return result;
        }
    }




