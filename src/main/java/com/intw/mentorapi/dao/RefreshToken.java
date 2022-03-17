package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshToken {

    private long idx;
    private String userEmail;
    private String accessToken;
    private String refreshToken;
    private String refreshTokenExpirationAt;
}
