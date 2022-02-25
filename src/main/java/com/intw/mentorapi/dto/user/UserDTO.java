package com.intw.mentorapi.dto.user;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.company.CompanyDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserDTO extends PageDTO {

    @ApiModelProperty(hidden = true)
    private long idx;

    @NotBlank
    @ApiModelProperty(value = "아이디", example = "admin@naver.com", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(value = "권한", example = "ADMIN_ROLE", required = true)
    private String role;

    @NotBlank
    @ApiModelProperty(value = "권한 상세", example = "ADMIN", required = true)
    private String roleDetail;

    @NotBlank
    @ApiModelProperty(value = "활성/비활성 여부", example = "Y", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "사용자명", example = "홍길동", required = true)
    private String name;

    public static class UserListDTO extends UserDTO {

    }

    @Getter
    @Setter
    public static class UserInsertDTO extends UserDTO {

        @NotBlank
        @ApiModelProperty(value = "비밀번호", example = "12345", required = true)
        private String password;

        @NotBlank
        @ApiModelProperty(value = "연락처", example = "010-1234-5678", required = true)
        private String phone;

        @NotBlank
        @ApiModelProperty(value = "성별", example = "M", required = true)
        private String gender;

        @NotBlank
        @ApiModelProperty(value = "동의 여부", example = "Y", required = true)
        private String isAgreement;

        @ApiModelProperty(value = "입사일", example = "2022-02-21")
        private String joinAt;

        private CompanyDTO companyDTO;
    }

    public static class UserFindOneDTO extends UserDTO {

        @NotBlank
        @ApiModelProperty(value = "연락처", example = "010-1234-5678", required = true)
        private String phone;

        @NotBlank
        @ApiModelProperty(value = "성별", example = "M", required = true)
        private String gender;

        @NotBlank
        @ApiModelProperty(value = "동의 여부", example = "Y", required = true)
        private String isAgreement;
    }
}
