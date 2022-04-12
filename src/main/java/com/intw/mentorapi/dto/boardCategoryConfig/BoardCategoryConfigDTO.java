package com.intw.mentorapi.dto.boardCategoryConfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Service
public class BoardCategoryConfigDTO {

    @NotBlank
    @ApiModelProperty(value = "카테고리 이름", example = "토팍", required = true)
    private String name;

    @Getter
    @Setter
    public static class BoardCategoryConfigInsertDTO extends BoardCategoryConfigDTO {

    }

    @Getter
    @Setter
    public static class BoardCategoryConfigUpdateDTO extends BoardCategoryConfigDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;

    }
}
