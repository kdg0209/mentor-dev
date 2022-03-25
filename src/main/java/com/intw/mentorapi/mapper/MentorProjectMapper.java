package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.MentorProject;
import org.mapstruct.Mapper;

@Mapper
public interface MentorProjectMapper {

    int isMentorProjectExist(long idx);

    void insertMentorProject(MentorProject mentorProject);
    void updateMentorProject(MentorProject mentorProject);
    void deleteMentorProject(long idx);
}
