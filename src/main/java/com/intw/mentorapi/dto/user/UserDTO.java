package com.intw.mentorapi.dto.user;

import com.intw.mentorapi.dto.company.CompanyDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class UserDTO {

    @NotBlank
    @ApiModelProperty(value = "아이디", example = "admin@naver.com", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(value = "비밀번호", example = "12345", required = true)
    private String password;

    @NotBlank
    @ApiModelProperty(value = "권한", example = "ROLE_USER", required = true)
    private String role;

    @Positive
    @ApiModelProperty(value = "권한  상세 코드", example = "100", required = true)
    private int roleCodeIdx;

    @NotBlank
    @ApiModelProperty(value = "활성/비활성 여부", example = "Y", required = true)
    private String status;

    @NotBlank
    @ApiModelProperty(value = "사용자명", example = "홍길동", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "연락처", example = "010-1234-5678", required = true)
    private String phone;

    @NotBlank
    @ApiModelProperty(value = "성별", example = "M", required = true)
    private String gender;

    @NotBlank
    @ApiModelProperty(value = "동의 여부", example = "Y", required = true)
    private String isAgreement;

    @Getter
    @Setter
    public static class UserInsertDTO extends UserDTO {

        @ApiModelProperty(value = "입사일", example = "2022-02-21")
        private String joinAt;

        @ApiModelProperty(value = "기업 정보")
        private CompanyDTO.CompanyInsertDTO companyDTO;
    }

    @Getter
    @Setter
    public static class UserUpdateDTO extends UserDTO {

        @Positive
        @ApiModelProperty(value = "번호", example = "1", required = true)
        private long idx;
    }
}
