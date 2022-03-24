package com.intw.mentorapi.dto.mentor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MentorListDTO {

    @ApiModelProperty(hidden = true)
    private long mentorIdx;

    @ApiModelProperty(value = "멘토명", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "멘토 이메일", example = "mentor@naver.com")
    private String userEmail;

    @ApiModelProperty(value = "멘토 연락처", example = "010*1234*5678")
    private String userPhone;

    @ApiModelProperty(value = "멘토명 가입일", example = "2022-01-01")
    private String mentorWriteAt;

    @ApiModelProperty(value = "멘토 상태", example = "Y")
    private String mentorStatus;

}
