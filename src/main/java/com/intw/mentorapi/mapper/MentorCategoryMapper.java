package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.MentorCategory;
import com.intw.mentorapi.dto.mentorCateogry.MentorCategoryDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MentorCategoryMapper {

    List<MentorCategoryDTO> findAllMentorCategory(long mentorIdx);
    void insertMentorCategory(@Param("mentorCategory") MentorCategory mentorCategory);
    void deleteMentorCategory(long mentorIdx);
}
