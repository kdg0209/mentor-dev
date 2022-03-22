package com.intw.mentorapi.service;

import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.Company;
import com.intw.mentorapi.dao.InquiryReply;
import com.intw.mentorapi.dto.company.CompanyDTO;
import com.intw.mentorapi.dto.inquiryReply.InquiryReplyDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.CompanyException;
import com.intw.mentorapi.exception.customException.InquiryException;
import com.intw.mentorapi.exception.customException.UserException;
import com.intw.mentorapi.mapper.InquiryMapper;
import com.intw.mentorapi.mapper.InquiryReplyMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryReplyService {

    private final InquiryReplyMapper inquiryReplyMapper;
    private final InquiryMapper inquiryMapper;
    private final ModelMapper modelMapper;

    public ApiResponse write(InquiryReplyDTO.InquiryReplyInsertDTO inquiryReplyInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isInquiryCount = inquiryMapper.isInquiryExist(inquiryReplyInsertDTO.getInquiryIdx());

        if (isInquiryCount == 0) {
            throw new InquiryException(ErrorCode.isInquiryNotFoundException);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        InquiryReply inquiryReply = modelMapper.map(inquiryReplyInsertDTO, InquiryReply.class);
        inquiryReply.setWriteIp(getUserIp.returnIP());
        inquiryReplyMapper.insertInquiryReply(inquiryReply);
        inquiryMapper.updateStatusByInquiryReply(inquiryReplyInsertDTO.getInquiryIdx());
        return result;
    }


    public ApiResponse update(InquiryReplyDTO.InquiryReplyUpdateDTO inquiryReplyUpdateDTO) {
        ResponseMap result = new ResponseMap();

        InquiryReply inquiryReply = modelMapper.map(inquiryReplyUpdateDTO, InquiryReply.class);
        inquiryReplyMapper.updateInquiryReply(inquiryReply);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        inquiryReplyMapper.deleteInquiryReplyByIdx(idx);
        return result;
    }

}
