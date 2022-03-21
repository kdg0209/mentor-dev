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
    isBusinessNumberExistException(601, "해당 사업자번호는 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    isBoardConfigTypeExistException(701, "해당 타입은 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isBoardConfigNotFoundException(701, "등록되어 있지 않은 게시판 모듈 입니다.", HttpStatus.BAD_REQUEST),

    isBoardCategoryConfigNameExistException(701, "해당 카테고리이름은 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isBoardCategoryConfigNotFoundException(701, "등록되어 있지 않은 카테고리 입니다.", HttpStatus.BAD_REQUEST),

    FileUploadFailException(701, "파일 업로드에 실패했습니다.", HttpStatus.BAD_REQUEST),
    InvalidFileExtensionException(701, "잘못된 형식의 파일 확장자입니다.", HttpStatus.BAD_REQUEST),

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
