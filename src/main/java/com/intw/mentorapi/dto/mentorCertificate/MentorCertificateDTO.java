package com.intw.mentorapi.dto.mentorCertificate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class MentorCertificateDTO {

    @NotBlank
    @ApiModelProperty(value = "자격증명", example = "SQLD", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "세부사항", example = "세부사항", required = true)
    private String contents;

    @NotBlank
    @ApiModelProperty(value = "발급 기관", example = "발급 기관", required = true)
    private String acceptanceOrganization;

    @NotBlank
    @ApiModelProperty(value = "유효 기간", example = "2022-01-01", required = true)
    private String expirationAt;

    @Setter
    @Getter
    public static class MentorCertificateInsertDTO extends MentorCertificateDTO {

        @Positive
        @ApiModelProperty(value = "멘토 코드", example = "1", required = true)
        private long mentorIdx;
    }

    @Setter
    @Getter
    public static class MentorCertificateUpdateDTO extends MentorCertificateDTO {

        @Positive
        @ApiModelProperty(value = "코드", example = "1", required = true)
        private long idx;
    }
}
