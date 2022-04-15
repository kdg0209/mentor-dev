package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.MentorCategoryConfig;
import com.intw.mentorapi.dto.mentorCategoryConfig.MentorCategoryConfigDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardCategoryConfigException;
import com.intw.mentorapi.exception.customException.MentorCategoryConfigException;
import com.intw.mentorapi.mapper.MentorCategoryConfigMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorCategoryConfigService {

    private final MentorCategoryConfigMapper mentorCategoryConfigMapper;

    public ApiResponse lists() {
        ResponseMap result = new ResponseMap();
        result.setResponseData("mentorCategoryConfigList", mentorCategoryConfigMapper.findAllMentorCategoryConfig());
        return result;
    }

    public ApiResponse write(MentorCategoryConfigDTO.MentorCategoryConfigInsertDTO params) {
        ResponseMap result = new ResponseMap();

        int isCategoryNameExist = mentorCategoryConfigMapper.isCategoryNameExist(params.getName());

        if (isCategoryNameExist > 0) {
            throw new MentorCategoryConfigException(ErrorCode.isMentorCategoryConfigNameExistException);
        }

        MentorCategoryConfig mentorCategoryConfig = MentorCategoryConfig.builder()
                                                        .name(params.getName())
                                                        .build();

        mentorCategoryConfigMapper.insertMentorCategoryConfig(mentorCategoryConfig);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("mentorCategoryConfig", mentorCategoryConfigMapper.findOneMentorCategoryConfig(idx));
        return result;
    }


    public ApiResponse update(MentorCategoryConfigDTO.MentorCategoryConfigUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isMentorCategoryExist = mentorCategoryConfigMapper.isMentorCategoryExist(params.getIdx());

        if (isMentorCategoryExist == 0) {
            throw new MentorCategoryConfigException(ErrorCode.isMentorCategoryConfigNotFoundException);
        }

        MentorCategoryConfig mentorCategoryConfig = MentorCategoryConfig.builder()
                                                        .idx(params.getIdx())
                                                        .name(params.getName())
                                                        .build();

        mentorCategoryConfigMapper.updateMentorCategoryConfig(mentorCategoryConfig);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        mentorCategoryConfigMapper.deleteMentorCategoryConfig(idx);
        return result;
    }
}
