package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class RoleCodeException extends RuntimeException {
    private ErrorCode errorCode;

    public RoleCodeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
