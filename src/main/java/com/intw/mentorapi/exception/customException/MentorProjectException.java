package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MentorProjectException extends RuntimeException {

    private ErrorCode errorCode;

    public MentorProjectException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
