package com.intw.mentorapi.service;

import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.InquiryReply;
import com.intw.mentorapi.dto.inquiryReply.InquiryReplyDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.InquiryException;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.InquiryMapper;
import com.intw.mentorapi.mapper.InquiryReplyMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryReplyService extends UserProvider {

    private final InquiryReplyMapper inquiryReplyMapper;
    private final InquiryMapper inquiryMapper;

    public ApiResponse write(InquiryReplyDTO.InquiryReplyInsertDTO inquiryReplyInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isInquiryCount = inquiryMapper.isInquiryExist(inquiryReplyInsertDTO.getInquiryIdx());

        if (isInquiryCount == 0) {
            throw new InquiryException(ErrorCode.isInquiryNotFoundException);
        }

        InquiryReply inquiryReply = new InquiryReply();
        inquiryReply.setInquiryIdx(inquiryReplyInsertDTO.getInquiryIdx());
        inquiryReply.setReply(inquiryReplyInsertDTO.getReply());
        inquiryReply.setUserIdx(getUser().getIdx());
        inquiryReply.setWriteIp(getUserIp.returnIP());
        inquiryReplyMapper.insertInquiryReply(inquiryReply);

        if (getUser().getRole().equals("ROLE_ADMIN")) {
            inquiryMapper.updateStatusByInquiryReply(inquiryReplyInsertDTO.getInquiryIdx(), "ROLE_ADMIN");
        }

        if (getUser().getRole().equals("ROLE_MANAGER")) {
            inquiryMapper.updateStatusByInquiryReply(inquiryReplyInsertDTO.getInquiryIdx(), "ROLE_MANAGER");
        }

        return result;
    }

    public ApiResponse update(InquiryReplyDTO.InquiryReplyUpdateDTO inquiryReplyUpdateDTO) {
        ResponseMap result = new ResponseMap();

        InquiryReply inquiryReply = new InquiryReply();
        inquiryReply.setIdx(inquiryReplyUpdateDTO.getIdx());
        inquiryReply.setReply(inquiryReplyUpdateDTO.getReply());

        boolean isAccessRole = roleCheck(getUser().getRole(), inquiryReplyUpdateDTO.getIdx());

        if (!isAccessRole) {
            throw new InquiryException(ErrorCode.ForbiddenException);
        }

        inquiryReplyMapper.updateInquiryReply(inquiryReply, getUser().getRole());
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        boolean isAccessRole = roleCheck(getUser().getRole(), idx);

        if (!isAccessRole) {
            throw new InquiryException(ErrorCode.ForbiddenException);
        }

        inquiryReplyMapper.deleteInquiryReply(idx, getUser().getRole(), getUser().getCompanyIdx());
        return result;
    }

    private boolean roleCheck(String role, long idx) {
        if (role.equals("ROLE_MANAGER")) {
            Integer companyIdx = inquiryReplyMapper.isCompanyExist(idx);

            if (companyIdx == null || companyIdx != getUser().getCompanyIdx()) {
                return false;
            }
        }
        return true;
    }
}
