package com.intw.mentorapi.dto.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
public class CompanyDTO {

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
    @ApiModelProperty(value = "기업 대표 이메일", example = "admin@admin.co.kr", required = true)
    private String email;

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
    @ApiModelProperty(value = "담당자 이름", example = "홍길동", required = true)
    private String managerName;

    @NotBlank
    @ApiModelProperty(value = "담당자 이메일", example = "admin@admin.co.kr", required = true)
    private String managerEmail;

    @NotBlank
    @ApiModelProperty(value = "담당자 연락처", example = "010-1234-5678", required = true)
    private String managerPhone;

    @NotNull
    @ApiModelProperty(value = "설립일", example = "2022-02-22", required = true)
    private LocalDate createAt;

    @Getter
    @Setter
    public static class CompanyInsertDTO extends CompanyDTO{

        @ApiModelProperty(value = "회원 참조 코드", example = "1")
        private long userIdx;
    }

    @Getter
    @Setter
    public static class CompanyUpdateDTO extends CompanyDTO{

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;

        @ApiModelProperty(value = "총 결제 시간", example = "1")
        private float totalTime;

        @ApiModelProperty(value = "총 이용 시간", example = "1")
        private float totalUsedTime;

        @ApiModelProperty(value = "잔여할당 시간", example = "1")
        private float availableTime;

        @NotNull
        @ApiModelProperty(value = "서비스 시작일", example = "2022-01-01", required = true)
        private LocalDate serviceStartAt;

        @NotNull
        @ApiModelProperty(value = "서비스 종료일", example = "2022-01-01", required = true)
        private LocalDate serviceEndAt;
    }
}
