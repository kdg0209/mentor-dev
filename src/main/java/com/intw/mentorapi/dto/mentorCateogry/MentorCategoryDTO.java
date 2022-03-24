package com.intw.mentorapi.dto.mentorCateogry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class MentorCategoryDTO {

    @NotBlank
    @ApiModelProperty(value = "이름", example = "개발", required = true)
    private String categoryName;
}
