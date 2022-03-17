package com.intw.mentorapi.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserViewDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(value = "사용자명", example = "홍길동")
    private String name;

    @ApiModelProperty(value = "아이디", example = "admin@naver.com")
    private String email;

    @ApiModelProperty(value = "권한", example = "ADMIN_ROLE")
    private String role;

    @ApiModelProperty(value = "권한 상세", example = "ADMIN")
    private String roleDetail;

    @ApiModelProperty(value = "활성/비활성 여부", example = "Y")
    private String status;

    @ApiModelProperty(value = "생성일", example = "2022-02-14")
    private String writeAt;

    @ApiModelProperty(value = "발문일", example = "2022-02-14")
    private String visitAt;
}
