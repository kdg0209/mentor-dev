package com.intw.mentorapi.dto.inquiry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class InquiryDTO {

    @Positive
    @ApiModelProperty(value = "회원 코드", example = "1", required = true)
    private long userIdx;

    @Positive
    @ApiModelProperty(value = "기업 코드", example = "1", required = true)
    private long companyIdx;

    @NotBlank
    @ApiModelProperty(value = "답변 여부", example = "N", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "문의 사항", example = "문의 사항", required = true)
    private String memo;

    @Setter
    @Getter
    public static class InquiryInsertDTO extends InquiryDTO {

    }

    @Setter
    @Getter
    public static class InquiryUpdateDTO extends InquiryDTO {

    }
}
