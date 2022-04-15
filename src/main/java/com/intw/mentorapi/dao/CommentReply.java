package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReply {

    private long idx;
    private long userIdx;
    private long commentIdx;
    private String reply;
    private String writeAt;
    private String writeIp;
}
