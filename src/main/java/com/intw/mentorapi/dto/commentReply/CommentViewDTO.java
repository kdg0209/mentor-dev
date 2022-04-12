package com.intw.mentorapi.dto.commentReply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class CommentViewDTO {

    @Positive
    @ApiModelProperty(value = "번호", example = "1", required = true)
    private long idx;

    @NotBlank
    @ApiModelProperty(value = "댓글 답변", example = "댓글 답변", required = true)
    private String reply;
}
