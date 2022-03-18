package com.intw.mentorapi.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardViewDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "작성자명", example = "홍길동")
    private String name;

    @ApiModelProperty(value = "아이디", example = "admin@naver.com")
    private String email;

    @ApiModelProperty(value = "제목", example = "제목...")
    private String title;

    @ApiModelProperty(value = "조회수", example = "100")
    private int viewCount;

    @ApiModelProperty(value = "작성일", example = "2022-01-01")
    private String writeAt;
}
