package com.intw.mentorapi.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class BoardDTO {

    @Positive
    @ApiModelProperty(value = "게시판 설정 코드", example = "1", required = true)
    private long boardConfigIdx;

    @Positive
    @ApiModelProperty(value = "게시판 카테고리 코드", example = "1", required = true)
    private long boardCategoryConfigIdx;

    @NotBlank
    @ApiModelProperty(value = "공개 / 비공개 / 삭제 여부", example = "Y", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "제목", example = "제목은", required = true)
    private String title;

    @NotBlank
    @ApiModelProperty(value = "내용", example = "내용은", required = true)
    private String contents;

    @ApiModelProperty(value = "첨부파일")
    List<MultipartFile> files;

    @ApiModelProperty(value = "첨부파일 종류", example = "files")
    private String targetType;


    @Setter
    @Getter
    public static class BoardInsertDTO extends BoardDTO {

    }

    @Getter
    @Setter
    public static class BoardUpdateDTO extends BoardDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
