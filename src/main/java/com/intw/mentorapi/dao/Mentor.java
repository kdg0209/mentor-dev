package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Mentor {

    private long idx;
    private long userIdx;
    private String status;
    private String isFreelancer;
    private int mentoringCount;
    private float careerYear;
    private String writeAt;
}
