package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.MentorCategoryConfig;
import com.intw.mentorapi.dto.mentorCategoryConfig.MentorCategoryConfigDTO;
import com.intw.mentorapi.exception.ErrorCode;
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

    public ApiResponse write(MentorCategoryConfigDTO.MentorCategoryConfigInsertDTO mentorCategoryConfigInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isCategoryNameExist = mentorCategoryConfigMapper.isCategoryNameExist(mentorCategoryConfigInsertDTO.getName());

        if (isCategoryNameExist > 0) {
            throw new MentorCategoryConfigException(ErrorCode.isMentorCategoryConfigNameExistException);
        }

        MentorCategoryConfig mentorCategoryConfig = new MentorCategoryConfig();
        mentorCategoryConfig.setName(mentorCategoryConfigInsertDTO.getName());

        mentorCategoryConfigMapper.insertMentorCategoryConfig(mentorCategoryConfig);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("mentorCategoryConfig", mentorCategoryConfigMapper.findOneMentorCategoryConfig(idx));
        return result;
    }


    public ApiResponse update(MentorCategoryConfigDTO.MentorCategoryConfigUpdateDTO mentorCategoryConfigUpdateDTO) {
        ResponseMap result = new ResponseMap();

        MentorCategoryConfig mentorCategoryConfig = new MentorCategoryConfig();
        mentorCategoryConfig.setIdx(mentorCategoryConfigUpdateDTO.getIdx());
        mentorCategoryConfig.setName(mentorCategoryConfigUpdateDTO.getName());

        mentorCategoryConfigMapper.updateMentorCategoryConfig(mentorCategoryConfig);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        mentorCategoryConfigMapper.deleteMentorCategoryConfig(idx);
        return result;
    }
}
