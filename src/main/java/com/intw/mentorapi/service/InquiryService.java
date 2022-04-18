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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryService extends UserProvider {

    private final InquiryMapper inquiryMapper;
    private final InquiryReplyMapper inquiryReplyMapper;

    /*
     ** 관리자 기능 시작
     */
    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("inquiryList", inquiryMapper.findAllInquiry(pageDTO, getUser().getRole().toString(), getUser().getCompanyIdx()));
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        InquiryViewDTO inquiry = inquiryMapper.findOneInquiry(idx, getUser().getRole().toString(), getUser().getCompanyIdx());

        if (inquiry != null) {
            result.setResponseData("inquiryReply", inquiryReplyMapper.findAllInquiryReply(idx, inquiry.getCompanyIdx()));
        }

        result.setResponseData("inquiry", inquiry);
        return result;
    }

    @Transactional
    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        inquiryMapper.deleteInquiry(idx, getUser().getRole().toString(), getUser().getCompanyIdx());
        inquiryReplyMapper.deleteAllInquiryReplyByInquiry(idx, getUser().getRole(), getUser().getCompanyIdx());
        return result;
    }

    /*
     ** 매니저 기능 시작
     */
    public ApiResponse managerWrite(InquiryDTO.InquiryInsertDTO params) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        Inquiry inquiry = Inquiry.builder()
                            .userIdx(getUser().getIdx())
                            .companyIdx(getUser().getCompanyIdx())
                            .memo(params.getMemo())
                            .writeIp(getUserIp.returnIP())
                            .build();
        inquiryMapper.insertInquiryByCompany(inquiry);
        return result;
    }

    @Transactional
    public ApiResponse managerUpdate(InquiryDTO.InquiryUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        Inquiry inquiry = Inquiry.builder()
                            .idx(params.getIdx())
                            .companyIdx(getUser().getCompanyIdx())
                            .memo(params.getMemo())
                            .build();

        inquiryMapper.updateInquiryByCompany(inquiry);
        return result;
    }
}
