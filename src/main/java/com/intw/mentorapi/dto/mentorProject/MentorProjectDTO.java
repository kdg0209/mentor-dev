package com.intw.mentorapi.dto.mentorProject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class MentorProjectDTO {

    @NotBlank
    @ApiModelProperty(value = "프로젝트명", example = "사수래", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "분야", example = "멘토-멘티", required = true)
    private String category;

    @NotBlank
    @ApiModelProperty(value = "담당 역할", example = "개발", required = true)
    private String role;

    @NotBlank
    @ApiModelProperty(value = "시작일", example = "2022-01-01", required = true)
    private String startAt;

    @NotBlank
    @ApiModelProperty(value = "종료일", example = "2022-12-31", required = true)
    private String endAt;

    @NotBlank
    @ApiModelProperty(value = "기타 내용", example = "기타 내용", required = true)
    private String etc;

    @Setter
    @Getter
    public static class MentorProjectInsertDTO extends MentorProjectDTO {

        @Positive
        @ApiModelProperty(value = "멘토 번호", example = "1", required = true)
        private long mentorIdx;
    }

    @Setter
    @Getter
    public static class MentorProjectUpdateDTO extends MentorProjectDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
