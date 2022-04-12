package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BoardCategoryConfigException extends RuntimeException {

    private ErrorCode errorCode;

    public BoardCategoryConfigException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
