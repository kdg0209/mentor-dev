package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MentorCategoryConfigException extends RuntimeException {

    private ErrorCode errorCode;

    public MentorCategoryConfigException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
