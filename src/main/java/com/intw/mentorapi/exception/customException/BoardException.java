package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private ErrorCode errorCode;

    public BoardException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
