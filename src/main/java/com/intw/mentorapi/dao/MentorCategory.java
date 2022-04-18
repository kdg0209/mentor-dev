package com.intw.mentorapi.dao;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorCategory {

    private long mentorIdx;
    private List<Integer> mentorCategoryConfigIdx;
}
