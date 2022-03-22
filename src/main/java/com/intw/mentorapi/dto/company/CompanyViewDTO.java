package com.intw.mentorapi.dto.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyViewDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "등록 미등록 여부", example = "Y")
    private String companyStatus;

    @ApiModelProperty(value = "기업명", example = "피자나라치킨공주")
    private String companyName;

    @ApiModelProperty(value = "기업 연락처", example = "02-123-4567")
    private String companyTel;

    @ApiModelProperty(value = "업종", example = "교육")
    private String companyCategory;

    @ApiModelProperty(value = "직원 수", example = "5인이상 10인미만")
    private String companyEmployeeCount;

    @ApiModelProperty(value = "기업 주소", example = "서울특별시")
    private String companyAddress;

    @ApiModelProperty(value = "법인 번호", example = "1234-5689-456")
    private String companyCorporationNumber;

    @ApiModelProperty(value = "사업자 번호", example = "7894-5612-1234")
    private String companyBusinessNumber;

    @ApiModelProperty(value = "담당자 이름", example = "홍길동")
    private String companyManagerName;

    @ApiModelProperty(value = "담당자 이메일", example = "admin@admin.co.kr")
    private String companyManagerEmail;

    @ApiModelProperty(value = "담당자 연락처", example = "010-1234-5678")
    private String companyManagerPhone;

    @ApiModelProperty(value = "총 결제 시간", example = "1")
    private float companyTotalTime;

    @ApiModelProperty(value = "총 이용 시간", example = "1")
    private float companyTotalUsedTime;

    @ApiModelProperty(value = "잔여할당 시간", example = "1")
    private float companyAvailableTime;

    @ApiModelProperty(value = "서비스 시작일", example = "2022-01-01")
    private String companyServiceStartAt;

    @ApiModelProperty(value = "서비스 종료일", example = "2022-01-01")
    private String companyServiceEndAt;

    @ApiModelProperty(value = "설립일", example = "2022-02-22")
    private String companyCreateAt;

    @ApiModelProperty(value = "회원명", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "회원 이메일", example = "admin@naver.com")
    private String userEmail;

    @ApiModelProperty(value = "회원 연락처", example = "010-1234-5678")
    private String userPhone;

    @ApiModelProperty(value = "회원 성별", example = "M")
    private String userGender;
}
