package com.intw.mentorapi.dto.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentListDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "작성한 사용자 번호", example = "1")
    private long userIdx;

    @ApiModelProperty(value = "작성한 작성자명", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "댓글", example = "댓글은...")
    private String comment;

    @ApiModelProperty(value = "작성일", example = "2022-01-01")
    private String writeAt;
}
