package com.intw.mentorapi.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorCertificate {

    private long idx;
    private long mentorIdx;
    private String name;
    private String contents;
    private String acceptanceOrganization;
    private String expirationAt;
    private String writeAt;
}
