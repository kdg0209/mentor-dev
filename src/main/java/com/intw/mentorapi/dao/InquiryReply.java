package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InquiryReply {

    private long idx;
    private long inquiryIdx;
    private long userIdx;
    private String reply;
    private String writeAt;
    private String writeIp;
}
