package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardConfig {

    private long idx;
    private String type;
    private String name;
    private String role;
    private int listCount;
    private int pageCount;
}
