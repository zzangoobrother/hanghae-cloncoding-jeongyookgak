package com.hanghae.hanghaecloncodingjeongyookgak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {HanghaeClonException.class})
    public ResponseEntity<Object> handleApiRequestException(HanghaeClonException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setResult("fail");
        restApiException.setHttpStatus(HttpStatus.OK);
        restApiException.setErrorMessage(ex.getErrorCode().getMessage());

        return new ResponseEntity<>(
                restApiException,
                HttpStatus.OK
        );
    }
}
