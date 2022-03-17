package com.intw.mentorapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

    UsernameOrPasswordNotFoundException (400, "아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    ForbiddenException(403, "해당 요청에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZEDException (401, "로그인 후 이용가능합니다.", HttpStatus.UNAUTHORIZED),
    ExpiredJwtException(444, "기존 토큰이 만료되었습니다. 새로운 토큰을 발급해주세요.", HttpStatus.UNAUTHORIZED),
    ReLogin(445, "모든 토큰이 만료되었습니다. 다시 로그인해주세요.", HttpStatus.UNAUTHORIZED),

    isEmailExistException(446, "해당 이메일이 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isPhoneExistException(446, "해당 연락처가 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    isCorporationNumberExistException(600, "해당 법인번호는 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isBusinessNumberExistException(601, "해당 사업자번호는 이미 존재합니다.", HttpStatus.BAD_REQUEST)

    ;

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private HttpStatus status;

    ErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
