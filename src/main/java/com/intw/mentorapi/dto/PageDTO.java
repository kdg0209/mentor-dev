package com.intw.mentorapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class PageDTO {

    @Positive
    @ApiModelProperty(value = "페이지 번호", example = "1", required = true)
    private String pageIndex;

    @Positive
    @ApiModelProperty(value = "데이터 목록 수", example = "10", required = true)
    private String pageSize;
}
