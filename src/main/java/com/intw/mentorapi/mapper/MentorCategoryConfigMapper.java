package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.MentorCategoryConfig;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MentorCategoryConfigMapper {

    int isMentorCategoryExist(long idx);
    int isCategoryNameExist(String name);
    int isCategoryContainsExist(@Param("categories") List<Integer> categories);

    List<MentorCategoryConfig> findAllMentorCategoryConfig();
    MentorCategoryConfig findOneMentorCategoryConfig(long idx);
    void insertMentorCategoryConfig(MentorCategoryConfig mentorCategoryConfig);
    void updateMentorCategoryConfig(MentorCategoryConfig mentorCategoryConfig);
    void deleteMentorCategoryConfig(long idx);
}
