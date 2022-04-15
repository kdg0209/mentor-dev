package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.MentorCategoryConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MentorCategoryConfigMapper {

    int isMentorCategoryExist(long idx);
    int isCategoryNameExist(String name);
    int isCategoryContainsExist(List<Integer> categoryIdxs);

    List<MentorCategoryConfig> findAllMentorCategoryConfig();
    MentorCategoryConfig findOneMentorCategoryConfig(long idx);
    void insertMentorCategoryConfig(MentorCategoryConfig mentorCategoryConfig);
    void updateMentorCategoryConfig(MentorCategoryConfig mentorCategoryConfig);
    void deleteMentorCategoryConfig(long idx);
}
