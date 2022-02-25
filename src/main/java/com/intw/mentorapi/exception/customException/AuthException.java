package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException{

    private  ErrorCode errorCode;

    public AuthException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
