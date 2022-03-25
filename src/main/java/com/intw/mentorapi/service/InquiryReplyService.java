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
        inquiryMapper.updateStatusByInquiryReply(inquiryReplyInsertDTO.getInquiryIdx(), getUser().getRole());
        return result;
    }

    public ApiResponse update(InquiryReplyDTO.InquiryReplyUpdateDTO inquiryReplyUpdateDTO) {
        ResponseMap result = new ResponseMap();

        InquiryReply inquiryReply = new InquiryReply();
        inquiryReply.setIdx(inquiryReplyUpdateDTO.getIdx());
        inquiryReply.setReply(inquiryReplyUpdateDTO.getReply());

        boolean isAccessRole = isAccessInquiryReply(getUser().getRole(), inquiryReplyUpdateDTO.getIdx());

        if (!isAccessRole) {
            throw new InquiryException(ErrorCode.ForbiddenException);
        }

        inquiryReplyMapper.updateInquiryReply(inquiryReply, getUser().getRole());
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        boolean isAccessRole = isAccessInquiryReply(getUser().getRole(), idx);

        if (!isAccessRole) {
            throw new InquiryException(ErrorCode.ForbiddenException);
        }

        inquiryReplyMapper.deleteInquiryReply(idx, getUser().getRole(), getUser().getCompanyIdx());
        return result;
    }

    /**
     * 기업 관리자가 접근 가능한 문의 답글인지 확인하는 메서드
     * @param role
     * @param idx
     */
    private boolean isAccessInquiryReply(String role, long idx) {
        if (role.equals("ROLE_MANAGER")) {
            Integer companyIdx = inquiryReplyMapper.isAccessible(idx);

            if (companyIdx == null || companyIdx != getUser().getCompanyIdx()) {
                return false;
            }
        }
        return true;
    }
}
