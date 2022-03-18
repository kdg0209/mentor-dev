package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BoardConfigException extends RuntimeException{

    private ErrorCode errorCode;

    public BoardConfigException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
