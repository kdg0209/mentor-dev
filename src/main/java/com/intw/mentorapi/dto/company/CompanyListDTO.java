package com.intw.mentorapi.dto.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
public class CompanyListDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "회원 참조 코드", example = "1")
    private long userIdx;

    @ApiModelProperty(value = "등록 미등록 여부", example = "Y")
    private String status;

    @ApiModelProperty(value = "기업명", example = "피자나라치킨공주")
    private String name;

    @ApiModelProperty(value = "기업 연락처", example = "02-123-4567")
    private String tel;

    @ApiModelProperty(value = "기업 주소", example = "서울특별시")
    private String address;

    @ApiModelProperty(value = "업종", example = "교육")
    private String category;

    @ApiModelProperty(value = "대표자명", example = "홍길동")
    private String ceo;

    @ApiModelProperty(value = "직원 수", example = "5인이상 10인미만")
    private String employeeCount;

    @ApiModelProperty(value = "법인 번호", example = "1234-5689-456")
    private String corporationNumber;

    @ApiModelProperty(value = "사업자 번호", example = "7894-5612-1234")
    private String businessNumber;

    @ApiModelProperty(value = "담당자 이름", example = "홍길동")
    private String managerName;

    @ApiModelProperty(value = "담당자 이메일", example = "admin@admin.co.kr")
    private String managerEmail;

    @ApiModelProperty(value = "담당자 연락처", example = "010-1234-5678")
    private String managerPhone;

    @ApiModelProperty(value = "총 결제 시간", example = "1")
    private float totalTime;

    @ApiModelProperty(value = "총 이용 시간", example = "1")
    private float totalUsedTime;

    @ApiModelProperty(value = "잔여할당 시간", example = "1")
    private float availableTime;

    @ApiModelProperty(value = "서비스 시작일", example = "2022-01-01")
    private String serviceStartAt;

    @ApiModelProperty(value = "서비스 종료일", example = "2022-01-01")
    private String serviceEndAt;

    @ApiModelProperty(value = "설립일", example = "2022-02-22")
    private String createAt;
}
