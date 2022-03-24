package com.intw.mentorapi.service;

import com.intw.mentorapi.common.Role;
import com.intw.mentorapi.dao.Mentor;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.mentor.MentorDTO;
import com.intw.mentorapi.dto.user.UserViewDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.*;
import com.intw.mentorapi.mapper.MentorCategoryConfigMapper;
import com.intw.mentorapi.mapper.MentorCategoryMapper;
import com.intw.mentorapi.mapper.MentorMapper;
import com.intw.mentorapi.mapper.UserMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final UserMapper userMapper;
    private final MentorMapper mentorMapper;
    private final MentorCategoryMapper mentorCategoryMapper;
    private final MentorCategoryConfigMapper mentorCategoryConfigMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();

        result.setResponseData("mentorList", mentorMapper.findAllMentor(pageDTO));
        return result;
    }

    public ApiResponse write(MentorDTO.MentorInsertDTO mentorInsertDTO) {
        ResponseMap result = new ResponseMap();

        UserViewDTO user = userMapper.findOneUser(mentorInsertDTO.getUserIdx());
        int isMentorRegisterCount = mentorMapper.isMentorRegistered(mentorInsertDTO.getUserIdx());
        int isCategoryContainsCount = mentorCategoryConfigMapper.isCategoryContainsExist(mentorInsertDTO.getMentorCategoryConfigIdx());

        if (isCategoryContainsCount != mentorInsertDTO.getMentorCategoryConfigIdx().size()) {
            throw new MentorCategoryConfigException(ErrorCode.InvalidMentorCategoryConfigException);
        }

        if (isMentorRegisterCount > 0) {
            throw new MentorException(ErrorCode.isMentorRegisteredException);
        }

        if (user == null) {
            throw new UserException(ErrorCode.isUserNotFoundException);
        }

        Mentor mentor = new Mentor();
        mentor.setUserIdx(mentorInsertDTO.getUserIdx());
        mentor.setStatus(mentorInsertDTO.getStatus());
        mentor.setIsFreelancer(mentorInsertDTO.getIFreelancer());
        mentorMapper.insertMentor(mentor);
        mentorCategoryMapper.insertMentorCategory(mentor.getIdx(), mentorInsertDTO.getMentorCategoryConfigIdx());
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("mentor", mentorMapper.findOneMentor(idx));
        result.setResponseData("mentorCategoryList", mentorCategoryMapper.findAllMentorCategory(idx));
        return result;
    }

    public ApiResponse update(MentorDTO.MentorUpdateDTO mentorUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorExist = mentorMapper.isMentorExist(mentorUpdateDTO.getIdx());
        int isCategoryContainsCount = mentorCategoryConfigMapper.isCategoryContainsExist(mentorUpdateDTO.getMentorCategoryConfigIdx());

        if (isMentorExist == 0) {
            throw new MentorException(ErrorCode.isMentorNotFoundException);
        }

        if (isCategoryContainsCount != mentorUpdateDTO.getMentorCategoryConfigIdx().size()) {
            throw new MentorCategoryConfigException(ErrorCode.InvalidMentorCategoryConfigException);
        }

        Mentor mentor = new Mentor();
        mentor.setIdx(mentorUpdateDTO.getIdx());
        mentor.setStatus(mentorUpdateDTO.getStatus());
        mentor.setIsFreelancer(mentorUpdateDTO.getIFreelancer());
        mentor.setMentoringCount(mentorUpdateDTO.getMentoringCount());
        mentor.setCareerYear(mentorUpdateDTO.getCareerYear());
        mentorMapper.updateMentor(mentor);
        mentorCategoryMapper.deleteMentorCategory(mentor.getIdx());
        mentorCategoryMapper.insertMentorCategory(mentor.getIdx(), mentorUpdateDTO.getMentorCategoryConfigIdx());
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        mentorMapper.deleteMentor(idx);
        mentorCategoryMapper.deleteMentorCategory(idx);
        return result;
    }
}
