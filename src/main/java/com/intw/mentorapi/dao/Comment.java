package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private long idx;
    private long userIdx;
    private long boardIdx;
    private String comment;
    private String writeAt;
    private String writeIp;
}
