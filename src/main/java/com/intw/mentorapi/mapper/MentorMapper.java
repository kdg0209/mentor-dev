package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Mentor;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.mentor.MentorListDTO;
import com.intw.mentorapi.dto.mentor.MentorViewDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MentorMapper {

    int isMentorRegistered(long idx);
    int isMentorExist(long idx);

    List<MentorListDTO> findAllMentor(@Param("pageDTO") PageDTO pageDTO);
    MentorViewDTO findOneMentor(long idx);
    void insertMentor(Mentor mentor);
    void updateMentor(Mentor mentor);
    void deleteMentor(long idx);
}
