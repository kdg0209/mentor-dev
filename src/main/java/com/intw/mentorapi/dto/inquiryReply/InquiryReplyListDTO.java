package com.intw.mentorapi.dto.inquiryReply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryReplyListDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "작성자", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "답변 내용", example = "답변 내용")
    private String reply;

    @ApiModelProperty(value = "작성일", example = "2022-01-01")
    private String writeAt;
}
