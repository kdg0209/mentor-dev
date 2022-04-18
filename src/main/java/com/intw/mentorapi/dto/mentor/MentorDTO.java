package com.intw.mentorapi.dto.mentor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
public class MentorDTO {

    @NotBlank
    @ApiModelProperty(value = "상태", example = "홍길동", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "프리랜서 여부", example = "홍길동", required = true)
    private String iFreelancer;

    @ApiModelProperty(value = "멘토 카테고리", example = "1, 2", required = true)
    private List<Integer> mentorCategoryConfigIdxs;

    @Setter
    @Getter
    public static class MentorInsertDTO extends MentorDTO {

        @Positive
        @ApiModelProperty(value = "회원 번호", example = "1", required = true)
        private long userIdx;
    }

    @Setter
    @Getter
    public static class MentorUpdateDTO extends MentorDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
