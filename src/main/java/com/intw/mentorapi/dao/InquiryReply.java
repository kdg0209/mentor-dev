package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryReply {

    private long idx;
    private long inquiryIdx;
    private long userIdx;
    private String reply;
    private String writeAt;
    private String writeIp;
}
