package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {

    private long idx;
    private long fkIdx;
    private String tables;
    private String targetType;
}
