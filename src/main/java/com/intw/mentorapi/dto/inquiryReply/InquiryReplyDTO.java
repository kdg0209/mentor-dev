package com.intw.mentorapi.dto.inquiryReply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class InquiryReplyDTO {

    @Positive
    @ApiModelProperty(value = "회원 코드", example = "1", required = true)
    private long userIdx;

    @NotBlank
    @ApiModelProperty(value = "문의 사항 답변", example = "문의 사항 답변", required = true)
    private String reply;

    @Setter
    @Getter
    public static class InquiryReplyInsertDTO extends InquiryReplyDTO{

        @Positive
        @ApiModelProperty(value = "문의 코드", example = "1", required = true)
        private long inquiryIdx;
    }

    @Setter
    @Getter
    public static class InquiryReplyUpdateDTO extends InquiryReplyDTO{

        @Positive
        @ApiModelProperty(value = "코드", example = "1", required = true)
        private long idx;
    }
}
