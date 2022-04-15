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

    public ApiResponse write(MentorProjectDTO.MentorProjectInsertDTO params) {
        ResponseMap result = new ResponseMap();

        int isMentorExist = mentorMapper.isMentorExist(params.getMentorIdx());

        if (isMentorExist == 0) {
            throw new MentorException(ErrorCode.isMentorNotFoundException);
        }

        MentorProject mentorProject = MentorProject.builder()
                                        .mentorIdx(params.getMentorIdx())
                                        .name(params.getName())
                                        .category(params.getCategory())
                                        .role(params.getRole())
                                        .startAt(params.getStartAt())
                                        .endAt(params.getEndAt())
                                        .etc(params.getEtc())
                                        .build();

        mentorProjectMapper.insertMentorProject(mentorProject);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();

        result.setResponseData("mentorProject", mentorProjectMapper.findOneMentorProject(idx));
        return result;
    }

    public ApiResponse update(MentorProjectDTO.MentorProjectUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isMentorProjectExist = mentorProjectMapper.isMentorProjectExist(params.getIdx());

        if (isMentorProjectExist == 0) {
            throw new MentorProjectException(ErrorCode.isMentorProjectNotFoundException);
        }

        MentorProject mentorProject = MentorProject.builder()
                                        .idx(params.getIdx())
                                        .name(params.getName())
                                        .category(params.getCategory())
                                        .role(params.getRole())
                                        .startAt(params.getStartAt())
                                        .endAt(params.getEndAt())
                                        .etc(params.getEtc())
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
