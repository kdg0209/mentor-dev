package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.Mentor;
import com.intw.mentorapi.dao.MentorCategory;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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

    @Transactional
    public ApiResponse write(MentorDTO.MentorInsertDTO params) {
        ResponseMap result = new ResponseMap();

        UserViewDTO user = userMapper.findOneUser(params.getUserIdx());
        int isMentorRegisterCount = mentorMapper.isMentorRegistered(params.getUserIdx());
        int isCategoryContainsCount = mentorCategoryConfigMapper.isCategoryContainsExist(params.getMentorCategoryConfigIdxs());

        if (isCategoryContainsCount != params.getMentorCategoryConfigIdxs().size()) {
            throw new MentorCategoryConfigException(ErrorCode.InvalidMentorCategoryConfigException);
        }

        if (isMentorRegisterCount > 0) {
            throw new MentorException(ErrorCode.isMentorRegisteredException);
        }

        if (user == null) {
            throw new UserException(ErrorCode.isUserNotFoundException);
        }

        Mentor mentor = Mentor.builder()
                            .userIdx(params.getUserIdx())
                            .status(params.getStatus())
                            .isFreelancer(params.getIFreelancer())
                            .build();

        mentorMapper.insertMentor(mentor);
        MentorCategory mentorCategory = MentorCategory.builder()
                                            .mentorIdx(mentor.getIdx())
                                            .mentorCategoryConfigIdx(params.getMentorCategoryConfigIdxs())
                                            .build();

        mentorCategoryMapper.insertMentorCategory(mentorCategory);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("mentor", mentorMapper.findOneMentor(idx));
        result.setResponseData("mentorCategoryList", mentorCategoryMapper.findAllMentorCategory(idx));
        return result;
    }

    @Transactional
    public ApiResponse update(MentorDTO.MentorUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isMentorExist = mentorMapper.isMentorExist(params.getIdx());
        int isCategoryContainsCount = mentorCategoryConfigMapper.isCategoryContainsExist(params.getMentorCategoryConfigIdxs());

        if (isMentorExist == 0) {
            throw new MentorException(ErrorCode.isMentorNotFoundException);
        }

        if (isCategoryContainsCount != params.getMentorCategoryConfigIdxs().size()) {
            throw new MentorCategoryConfigException(ErrorCode.InvalidMentorCategoryConfigException);
        }

        Mentor mentor = Mentor.builder()
                            .idx(params.getIdx())
                            .status(params.getStatus())
                            .isFreelancer(params.getIFreelancer())
                            .build();
        mentorMapper.updateMentor(mentor);

        MentorCategory mentorCategory = MentorCategory.builder()
                .mentorIdx(mentor.getIdx())
                .mentorCategoryConfigIdx(params.getMentorCategoryConfigIdxs())
                .build();
        mentorCategoryMapper.deleteMentorCategory(mentor.getIdx());
        mentorCategoryMapper.insertMentorCategory(mentorCategory);
        return result;
    }

    @Transactional
    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        mentorMapper.deleteMentor(idx);
        mentorCategoryMapper.deleteMentorCategory(idx);
        return result;
    }
}
