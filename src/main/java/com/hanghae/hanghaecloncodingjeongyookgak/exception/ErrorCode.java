package com.hanghae.hanghaecloncodingjeongyookgak.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum  ErrorCode {

    PRODUCT_NOT_FOUND(BAD_REQUEST, "해당 상품이 없습니다."),
    CART_NOT_FOUND(BAD_REQUEST, "해당 장바구니 상품이 없습니다."),

    EMAIL_DUPLICATE(BAD_REQUEST, "중복된 email이 존재합니다."),
    USER_NOT_FOUND(BAD_REQUEST, "회원 정보를 찾을 수 없습니다."),

    NICKNAME_DUPLICATE(BAD_REQUEST, "중복된 닉네임이 존재합니다."),

    LOGIN_TOKEN_EXPIRE(BAD_REQUEST, "로그인이 만료되었습니다. 재로그인 하세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
