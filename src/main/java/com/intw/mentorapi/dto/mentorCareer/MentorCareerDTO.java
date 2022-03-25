package com.intw.mentorapi.dto.mentorCareer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class MentorCareerDTO {

    @NotBlank
    @ApiModelProperty(value = "상태", example = "N", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "회사명", example = "인트윈", required = true)
    private String company;

    @NotBlank
    @ApiModelProperty(value = "부서", example = "개발팀", required = true)
    private String department;

    @NotBlank
    @ApiModelProperty(value = "직위", example = "사장", required = true)
    private String grade;

    @NotBlank
    @ApiModelProperty(value = "시작일", example = "2022-01-01", required = true)
    private String startAt;

    @NotBlank
    @ApiModelProperty(value = "종료일", example = "2022-12-31", required = true)
    private String endAt;

    @Setter
    @Getter
    public static class MentorCareerInsertDTO extends MentorCareerDTO {

        @Positive
        @ApiModelProperty(value = "멘토 번호", example = "1", required = true)
        private long mentorIdx;
    }

    @Setter
    @Getter
    public static class MentorCareerUpdateDTO extends MentorCareerDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
