package com.intw.mentorapi.dto.mentorCategoryConfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class MentorCategoryConfigDTO {

    @NotBlank
    @ApiModelProperty(value = "이름", example = "개발", required = true)
    private String name;

    @Setter
    @Getter
    public static class MentorCategoryConfigInsertDTO extends MentorCategoryConfigDTO {

    }

    @Setter
    @Getter
    public static class MentorCategoryConfigUpdateDTO extends MentorCategoryConfigDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
