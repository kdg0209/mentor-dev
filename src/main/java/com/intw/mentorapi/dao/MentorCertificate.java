package com.intw.mentorapi.dao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentorCertificate {

    private long idx;
    private long mentorIdx;
    private String name;
    private String contents;
    private String acceptanceOrganization;
    private String expirationAt;
    private String writeAt;
}
