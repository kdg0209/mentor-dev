package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InquiryException extends RuntimeException {

    private ErrorCode errorCode;

    public InquiryException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
