package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Comment {

    private long idx;
    private long userIdx;
    private long boardIdx;
    private String comment;
    private String writeAt;
    private String writeIp;
}
