package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryConfig {

    private long idx;
    private String name;
    private String writeAt;
    private String updateAt;
}
