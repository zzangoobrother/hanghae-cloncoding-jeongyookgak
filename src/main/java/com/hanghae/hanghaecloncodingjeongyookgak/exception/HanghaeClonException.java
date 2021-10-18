package com.hanghae.hanghaecloncodingjeongyookgak.exception;

import lombok.Getter;

@Getter
public class HanghaeClonException extends RuntimeException {

    private final ErrorCode errorCode;

    public HanghaeClonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
