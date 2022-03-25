package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MentorCertificateException extends RuntimeException {
    private ErrorCode errorCode;

    public MentorCertificateException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
