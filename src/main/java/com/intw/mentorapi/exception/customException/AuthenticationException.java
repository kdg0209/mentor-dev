package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException{

    private final ErrorCode errorCode;

    public AuthenticationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
