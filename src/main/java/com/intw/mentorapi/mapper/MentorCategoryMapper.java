package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dto.mentorCateogry.MentorCategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MentorCategoryMapper {

    List<MentorCategoryDTO> findAllMentorCategory(long mentorIdx);
    void insertMentorCategory(long mentorIdx, List<Integer> mentorCategoryConfigIdx);
    void deleteMentorCategory(long mentorIdx);
}
