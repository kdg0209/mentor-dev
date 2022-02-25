package com.intw.mentorapi.exception;

import com.intw.mentorapi.exception.customException.AuthException;
import com.intw.mentorapi.exception.customException.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBindException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List errorList = new ArrayList();

        for (FieldError fieldError : errors) {
            Map err = new HashMap();
            err.put("fieldName", fieldError.getField());
            err.put("message", fieldError.getDefaultMessage());
            errorList.add(err);
        }
        ErrorResponse errorResponse = new ErrorResponse(400, errorList);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     **** 로그인시 아이디 또는 비밀번호가 일치하지 않는 예외가 발생했을 때
     **/
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     **** 회원 가입시 중복 체크 예외 처리
     **/
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
