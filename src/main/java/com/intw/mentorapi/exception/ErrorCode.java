package com.intw.mentorapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

    isUserNotFoundException (400, "해당 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    UsernameOrPasswordNotFoundException (400, "아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    ForbiddenException(403, "해당 요청에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZEDException (401, "로그인 후 이용가능합니다.", HttpStatus.UNAUTHORIZED),
    ExpiredJwtException(444, "기존 토큰이 만료되었습니다. 새로운 토큰을 발급해주세요.", HttpStatus.UNAUTHORIZED),
    ReLogin(445, "모든 토큰이 만료되었습니다. 다시 로그인해주세요.", HttpStatus.UNAUTHORIZED),

    isRoleNotFoundException(446, "해당 권한은 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    isRoleExistException(446, "해당 권한은 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    isEmailExistException(446, "해당 이메일이 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isPhoneExistException(446, "해당 연락처가 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    isCompanyNotFoundException(600, "해당 기업은 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    isCorporationNumberExistException(600, "해당 법인번호는 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isBusinessNumberExistException(601, "해당 사업자번호는 이미 존재합니다.", HttpStatus.BAD_REQUEST),

    isBoardConfigTypeExistException(701, "해당 타입은 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isBoardConfigNotFoundException(701, "등록되어 있지 않은 게시판 모듈 입니다.", HttpStatus.BAD_REQUEST),

    isBoardNotFoundException(701, "게시물이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    isBoardCategoryConfigNameExistException(701, "해당 카테고리이름은 이미 존재합니다.", HttpStatus.BAD_REQUEST),
    isBoardCategoryConfigNotFoundException(701, "등록되어 있지 않은 카테고리 입니다.", HttpStatus.BAD_REQUEST),

    FileUploadFailException(701, "파일 업로드에 실패했습니다.", HttpStatus.BAD_REQUEST),
    InvalidFileExtensionException(701, "잘못된 형식의 파일 확장자입니다.", HttpStatus.BAD_REQUEST),

    isInquiryNotFoundException(701, "등록되어 있지 않은 문의 입니다.", HttpStatus.BAD_REQUEST),

    isCommentNotFoundException(600, "해당 댓글은 존재하지 않습니다.", HttpStatus.BAD_REQUEST),

    isMentorNotFoundException(701, "등록되어 있지 않은 멘토 입니다.", HttpStatus.BAD_REQUEST),
    isMentorRegisteredException(701, "이미 등록되어 있는 멘토 입니다.", HttpStatus.BAD_REQUEST),
    InvalidMentorCareerException(701, "잘못된 형식의 멘토 경력 양식입니다.", HttpStatus.BAD_REQUEST),
    InvalidMentorCategoryConfigException(600, "잘못된 카테고리 선택입니다", HttpStatus.BAD_REQUEST),
    isMentorCategoryConfigNameExistException(600, "해당 카테고리명은 존재합니다.", HttpStatus.BAD_REQUEST),


    isMentorCareerNotFoundException(701, "등록되어 있지 않은 멘토 경력 입니다.", HttpStatus.BAD_REQUEST),
    isMentorProjectNotFoundException(701, "등록되어 있지 않은 멘토 프로젝트 입니다.", HttpStatus.BAD_REQUEST),

    isMentorCertificateNotFoundException(701, "등록되어 있지 않은 멘토 자격증 입니다.", HttpStatus.BAD_REQUEST),
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
