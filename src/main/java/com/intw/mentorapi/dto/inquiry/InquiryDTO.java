package com.intw.mentorapi.dto.inquiry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class InquiryDTO {

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

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
