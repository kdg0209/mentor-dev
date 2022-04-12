package com.intw.mentorapi.dto.boardCategoryConfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCategoryConfigViewDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "카테고리 이름", example = "토픽")
    private String name;

    @ApiModelProperty(value = "작성일", example = "2022-01-01")
    private String writeAt;

    @ApiModelProperty(value = "수정일", example = "2022-01-01")
    private String updateAt;
}
