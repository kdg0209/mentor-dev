package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private long idx;
    private long boardConfigIdx;
    private long boardCategoryConfigIdx;
    private long userIdx;
    private String status;
    private String title;
    private String contents;
    private int viewCount;
    private String writeAt;
    private String updateAt;

}
