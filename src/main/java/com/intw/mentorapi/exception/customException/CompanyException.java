package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CompanyException extends RuntimeException{

    private ErrorCode errorCode;

    public CompanyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
