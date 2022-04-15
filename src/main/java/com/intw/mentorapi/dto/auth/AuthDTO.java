package com.intw.mentorapi.dto.auth;

import com.intw.mentorapi.config.Enum;
import com.intw.mentorapi.status.RoleStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class AuthDTO {

    /**
     * 로그인 시 사용하는 DTO
     */
    @Getter
    @Setter
    public static class LoginDTO {
        @NotBlank
        @ApiModelProperty(value = "아이디", example = "admin@naver.com", required = true)
        private String email;

        @NotBlank
        @ApiModelProperty(value = "비밀번호", example = "12345", required = true)
        private String password;
    }

    /**
     * Refresh Token을 사용하여 새로운 Access Token을 발급받을 때 사용하는 DTO
     */
    @Getter
    @Setter
    public static class GetNewAccessTokenDTO {

        @ApiModelProperty(value = "Refresh Token Index", example = "1", required = true)
        private long refreshIdx;
    }

    /**
     * 회원 가입 시 사용하는 DTO
     */
    @Getter
    @Setter
    public static class JoinDTO {

        @NotBlank
        @ApiModelProperty(value = "아이디", example = "admin@naver.com", required = true)
        private String email;

        @NotBlank
        @ApiModelProperty(value = "비밀번호", example = "12345", required = true)
        private String password;

        @Enum(enumClass = RoleStatus.class, ignoreCase = true)
        @ApiModelProperty(value = "권한", example = "ROLE_ADMIN", required = true)
        private String role;

        @Positive
        @ApiModelProperty(value = "권한  상세 코드", example = "100", required = true)
        private int roleCodeIdx;

        @NotBlank
        @ApiModelProperty(value = "회원 활성/비활성 여부", example = "Y", required = true)
        private String status;

        @NotBlank
        @ApiModelProperty(value = "이름", example = "홍길동", required = true)
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

        @ApiModelProperty(value = "입사일", example = "2022-02-21")
        private LocalDate joinAt;
    }
}
