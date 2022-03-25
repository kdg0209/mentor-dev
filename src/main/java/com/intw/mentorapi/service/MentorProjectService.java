package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.MentorProject;
import com.intw.mentorapi.dto.mentorProject.MentorProjectDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.MentorException;
import com.intw.mentorapi.exception.customException.MentorProjectException;
import com.intw.mentorapi.mapper.MentorMapper;
import com.intw.mentorapi.mapper.MentorProjectMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorProjectService {

    private final MentorMapper mentorMapper;
    private final MentorProjectMapper mentorProjectMapper;

    public ApiResponse write(MentorProjectDTO.MentorProjectInsertDTO mentorProjectInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorExist = mentorMapper.isMentorExist(mentorProjectInsertDTO.getMentorIdx());

        if (isMentorExist == 0) {
            throw new MentorException(ErrorCode.isMentorNotFoundException);
        }

        MentorProject mentorProject = MentorProject.builder()
                    .mentorIdx(mentorProjectInsertDTO.getMentorIdx())
                    .name(mentorProjectInsertDTO.getName())
                    .category(mentorProjectInsertDTO.getCategory())
                    .role(mentorProjectInsertDTO.getRole())
                    .startAt(mentorProjectInsertDTO.getStartAt())
                    .endAt(mentorProjectInsertDTO.getEndAt())
                    .etc(mentorProjectInsertDTO.getEtc())
                    .build();

        mentorProjectMapper.insertMentorProject(mentorProject);
        return result;
    }

    public ApiResponse update(MentorProjectDTO.MentorProjectUpdateDTO mentorProjectUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorProjectExist = mentorProjectMapper.isMentorProjectExist(mentorProjectUpdateDTO.getIdx());

        if (isMentorProjectExist == 0) {
            throw new MentorProjectException(ErrorCode.isMentorProjectNotFoundException);
        }

        MentorProject mentorProject = MentorProject.builder()
                    .idx(mentorProjectUpdateDTO.getIdx())
                    .name(mentorProjectUpdateDTO.getName())
                    .category(mentorProjectUpdateDTO.getCategory())
                    .role(mentorProjectUpdateDTO.getRole())
                    .startAt(mentorProjectUpdateDTO.getStartAt())
                    .endAt(mentorProjectUpdateDTO.getEndAt())
                    .etc(mentorProjectUpdateDTO.getEtc())
                    .build();

        mentorProjectMapper.updateMentorProject(mentorProject);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        mentorProjectMapper.deleteMentorProject(idx);
        return result;
    }
}
