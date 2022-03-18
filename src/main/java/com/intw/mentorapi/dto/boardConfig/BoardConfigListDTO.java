package com.intw.mentorapi.dto.boardConfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardConfigListDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "모듈 타입", example = "notice")
    private String type;

    @ApiModelProperty(value = "모듈 이름", example = "공지사항")
    private String name;

    @ApiModelProperty(value = "유저 권한 설정", example = "100")
    private int role;

    @ApiModelProperty(value = "페이지 데이터 갯수", example = "10")
    private int listCount;

    @ApiModelProperty(value = "페이지 목록 갯수", example = "5")
    private int pageCount;
}
