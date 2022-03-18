package com.intw.mentorapi.dto.boardConfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class BoardConfigDTO {

    @NotBlank
    @ApiModelProperty(value = "모듈 이름", example = "공지사항", required = true)
    private String name;

    @Positive
    @ApiModelProperty(value = "유저 권한 설정", example = "100", required = true)
    private int role;

    @Positive
    @ApiModelProperty(value = "페이지 데이터 갯수", example = "10", required = true)
    private int listCount;

    @Positive
    @ApiModelProperty(value = "페이지 목록 갯수", example = "5", required = true)
    private int pageCount;

    @Getter
    @Setter
    public static class BoardConfigInsertDTO extends BoardConfigDTO {
        @NotBlank
        @ApiModelProperty(value = "모듈 타입", example = "notice", required = true)
        private String type;
    }

    @Getter
    @Setter
    public static class BoardConfigUpdateDTO extends BoardConfigDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;

    }
}
