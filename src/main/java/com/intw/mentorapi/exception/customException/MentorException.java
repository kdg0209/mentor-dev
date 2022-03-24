package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MentorException extends RuntimeException {

    private ErrorCode errorCode;

    public MentorException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
