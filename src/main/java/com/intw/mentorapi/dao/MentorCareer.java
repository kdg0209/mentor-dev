package com.intw.mentorapi.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorCareer {

    private long idx;
    private long mentorIdx;
    private String status;
    private String company;
    private String department;
    private String grade;
    private double annual;
    private String startAt;
    private String endAt;
    private String writeAt;
}
