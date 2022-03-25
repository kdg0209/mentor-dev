package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MentorCareerException extends RuntimeException {

    private ErrorCode errorCode;

    public MentorCareerException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
