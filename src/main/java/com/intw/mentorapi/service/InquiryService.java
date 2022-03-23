package com.intw.mentorapi.service;


import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.Inquiry;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.inquiry.InquiryDTO;
import com.intw.mentorapi.dto.inquiry.InquiryViewDTO;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.InquiryMapper;
import com.intw.mentorapi.mapper.InquiryReplyMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService extends UserProvider {

    private final InquiryMapper inquiryMapper;
    private final InquiryReplyMapper inquiryReplyMapper;

    /*
     ** 관리자 기능 시작
     */
    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("inquiryList", inquiryMapper.findAllInquiry(pageDTO, getUser().getRole(), getUser().getCompanyIdx()));
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        InquiryViewDTO inquiry = inquiryMapper.findOneInquiry(idx, getUser().getRole(), getUser().getCompanyIdx());

        if (inquiry != null) {
            result.setResponseData("inquiryReply", inquiryReplyMapper.findAllInquiryReply(idx, inquiry.getCompanyIdx()));
        }

        result.setResponseData("inquiry", inquiry);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        inquiryMapper.deleteInquiry(idx, getUser().getRole(), getUser().getCompanyIdx());
        return result;
    }

    /*
     ** 매니저 기능 시작
     */
    public ApiResponse managerWrite(InquiryDTO.InquiryInsertDTO inquiryInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        Inquiry inquiry = new Inquiry();
        inquiry.setUserIdx(getUser().getIdx());
        inquiry.setCompanyIdx(getUser().getCompanyIdx());
        inquiry.setMemo(inquiryInsertDTO.getMemo());
        inquiry.setWriteIp(getUserIp.returnIP());
        inquiryMapper.insertInquiryByCompany(inquiry);
        return result;
    }

    public ApiResponse managerUpdate(InquiryDTO.InquiryUpdateDTO inquiryUpdateDTO) {
        ResponseMap result = new ResponseMap();

        Inquiry inquiry = new Inquiry();
        inquiry.setIdx(inquiryUpdateDTO.getIdx());
        inquiry.setCompanyIdx(getUser().getCompanyIdx());
        inquiry.setMemo(inquiryUpdateDTO.getMemo());
        inquiryMapper.updateInquiryByCompany(inquiry);
        return result;
    }
}
