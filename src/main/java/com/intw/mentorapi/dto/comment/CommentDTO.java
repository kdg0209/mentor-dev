package com.intw.mentorapi.dto.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class CommentDTO {

    @Positive
    @ApiModelProperty(value = "게시판 번호", example = "1", required = true)
    private long boardIdx;

    @NotBlank
    @ApiModelProperty(value = "댓글", example = "댓글", required = true)
    private String comment;

    @Setter
    @Getter
    public static class CommentInsertDTO extends CommentDTO {

    }

    @Setter
    @Getter
    public static class CommentUpdateDTO extends CommentDTO {
        @Positive
        @ApiModelProperty(value = "댓글 번호", example = "1", required = true)
        private long idx;
    }
}
