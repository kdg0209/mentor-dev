package com.intw.mentorapi.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorProject {

    private long idx;
    private long mentorIdx;
    private String name;
    private String category;
    private String role;
    private String startAt;
    private String endAt;
    private String etc;
    private String writeAt;
}
