package com.intw.mentorapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class PageDTO {

    @PositiveOrZero
    @ApiModelProperty(value = "페이지 번호", example = "0", required = true)
    private int pageIndex;

    @Positive
    @ApiModelProperty(value = "데이터 목록 수", example = "10", required = true)
    private int pageSize;

    @ApiModelProperty(value = "검색 단어", example = "홍길동")
    private String keyword;
}
