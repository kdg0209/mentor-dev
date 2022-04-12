package com.intw.mentorapi.dto.commentReply;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class CommentReplyDTO {

    @Positive
    @ApiModelProperty(value = "댓글 번호", example = "1", required = true)
    private long commentIdx;

    @NotBlank
    @ApiModelProperty(value = "댓글 답변", example = "댓글 답변", required = true)
    private String reply;

    @Setter
    @Getter
    public static class CommentReplyInsertDTO extends CommentReplyDTO {

    }

    @Setter
    @Getter
    public static class CommentReplyUpdateDTO extends CommentReplyDTO {

        @Positive
        @ApiModelProperty(value = "댓글 답변 번호", example = "1", required = true)
        private long idx;
    }
}
