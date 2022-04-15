package com.intw.mentorapi.dao;

import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {

    private long idx;
    private long companyIdx;
    private long userIdx;
    private String status;
    private String memo;
    private String writeAt;
    private String writeIp;
}
