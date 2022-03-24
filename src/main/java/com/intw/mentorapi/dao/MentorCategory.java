package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MentorCategory {

    private long mentorIdx;
    private List<Integer> mentorCategoryConfigIdx;
}
