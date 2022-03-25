package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.MentorCareer;
import org.mapstruct.Mapper;

@Mapper
public interface MentorCareerMapper {

    int isMentorCareerExist(long idx);

    MentorCareer findOneMentorCareer(long idx);
    void insertMentorCareer(MentorCareer mentorCareer);
    void updateMentorCareer(MentorCareer mentorCareer);
    void deleteMentorCareer(long idx);
}
