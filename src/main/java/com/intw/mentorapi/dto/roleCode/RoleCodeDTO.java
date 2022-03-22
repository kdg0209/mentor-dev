package com.intw.mentorapi.dto.roleCode;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class RoleCodeDTO {

    @Positive
    @ApiModelProperty(value = "권한 코드", example = "100", required = true)
    private int code;

    @NotBlank
    @ApiModelProperty(value = "권한명", example = "최종 관리자", required = true)
    private String name;
}
