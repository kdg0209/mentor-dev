package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardConfig {

    private long idx;
    private String type;
    private int role;
    private String name;
    private int listCount;
    private int pageCount;
}
