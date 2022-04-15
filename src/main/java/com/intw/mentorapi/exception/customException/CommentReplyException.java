package com.intw.mentorapi.exception.customException;

import com.intw.mentorapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CommentReplyException extends RuntimeException{

    private ErrorCode errorCode;

    public CommentReplyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
