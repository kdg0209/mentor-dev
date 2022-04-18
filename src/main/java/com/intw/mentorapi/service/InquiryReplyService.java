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
import com.intw.mentorapi.status.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryReplyService extends UserProvider {

    private final InquiryReplyMapper inquiryReplyMapper;
    private final InquiryMapper inquiryMapper;

    @Transactional
    public ApiResponse write(InquiryReplyDTO.InquiryReplyInsertDTO parms) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isInquiryCount = inquiryMapper.isInquiryExist(parms.getInquiryIdx());

        if (isInquiryCount == 0) {
            throw new InquiryException(ErrorCode.isInquiryNotFoundException);
        }

        InquiryReply inquiryReply = InquiryReply.builder()
                                        .inquiryIdx(parms.getInquiryIdx())
                                        .reply(parms.getReply())
                                        .userIdx(getUser().getIdx())
                                        .writeIp(getUserIp.returnIP())
                                        .build();

        inquiryReplyMapper.insertInquiryReply(inquiryReply);
        inquiryMapper.updateStatusByInquiryReply(inquiryReply.getInquiryIdx(), getUser().getRole().toString());
        return result;
    }

    @Transactional
    public ApiResponse update(InquiryReplyDTO.InquiryReplyUpdateDTO parms) {
        ResponseMap result = new ResponseMap();

        InquiryReply inquiryReply = InquiryReply.builder()
                                        .idx(parms.getIdx())
                                        .reply(parms.getReply())
                                        .build();

        boolean isAccessRole = isAccessInquiryReply(getUser().getRole(), parms.getIdx());

        if (!isAccessRole) {
            throw new InquiryException(ErrorCode.ForbiddenException);
        }

        inquiryReplyMapper.updateInquiryReply(inquiryReply, getUser().getRole());
        return result;
    }

    @Transactional
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
    private boolean isAccessInquiryReply(RoleStatus role, long idx) {
        if (role.equals("ROLE_MANAGER")) {
            Integer companyIdx = inquiryReplyMapper.isAccessible(idx);

            if (companyIdx == null || companyIdx != getUser().getCompanyIdx()) {
                return false;
            }
        }
        return true;
    }
}
