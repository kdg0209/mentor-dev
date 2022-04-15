package com.intw.mentorapi.dao;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private long idx;
    private long boardConfigIdx;
    private long boardCategoryConfigIdx;
    private long userIdx;
    private String status;
    private String title;
    private String contents;
    private int viewCount;
    private LocalDateTime writeAt;
    private LocalDateTime updateAt;

}
