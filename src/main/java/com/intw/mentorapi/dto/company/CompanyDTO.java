package com.intw.mentorapi.dto.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @NotBlank
    @ApiModelProperty(value = "등록 미등록 여부", example = "Y", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "기업명", example = "피자나라치킨공주", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "기업 연락처", example = "02-123-4567", required = true)
    private String tel;

    @NotBlank
    @ApiModelProperty(value = "기업 주소", example = "서울특별시", required = true)
    private String address;

    @NotBlank
    @ApiModelProperty(value = "업종", example = "교육", required = true)
    private String category;

    @NotBlank
    @ApiModelProperty(value = "대표자명", example = "홍길동", required = true)
    private String ceo;

    @NotBlank
    @ApiModelProperty(value = "직원 수", example = "5인이상 10인미만", required = true)
    private String employeeCount;

    @NotBlank
    @ApiModelProperty(value = "법인 번호", example = "1234-5689-456", required = true)
    private String corporationNumber;

    @NotBlank
    @ApiModelProperty(value = "사업자 번호", example = "7894-5612-1234", required = true)
    private String businessNumber;

    @NotBlank
    @ApiModelProperty(value = "설립일", example = "2022-02-22", required = true)
    private String createAt;
}
