package com.intw.mentorapi.dto.inquiry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InquiryViewDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "답변 여부", example = "N")
    private String status;

    @ApiModelProperty(value = "기업명 코드", example = "1")
    private long companyIdx;

    @ApiModelProperty(value = "기업명", example = "인트윈")
    private String companyName;

    @ApiModelProperty(value = "작성자", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "문의 내용", example = "문의 내용")
    private String memo;

    @ApiModelProperty(value = "작성일", example = "2022-01-01")
    private String writeAt;
}
