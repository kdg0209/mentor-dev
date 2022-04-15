package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    private long idx;
    private long userIdx;
    private String status;
    private String isFreelancer;
    private int mentoringCount;
    private String writeAt;
}
