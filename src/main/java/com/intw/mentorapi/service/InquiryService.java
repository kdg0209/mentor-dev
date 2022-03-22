package com.intw.mentorapi.service;


import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.Inquiry;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.inquiry.InquiryDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.AuthenticationException;
import com.intw.mentorapi.exception.customException.CompanyException;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.CompanyMapper;
import com.intw.mentorapi.mapper.InquiryMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService extends UserProvider {

    private final InquiryMapper inquiryMapper;
    private final CompanyMapper companyMapper;
    private final ModelMapper modelMapper;

    /*
     ** 관리자 기능 시작
     */
    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("inquiryList", inquiryMapper.findAllInquiry(pageDTO));
        return result;
    }

    public ApiResponse write(InquiryDTO.InquiryInsertDTO inquiryInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isCompanyCount = companyMapper.isCompanyExist(inquiryInsertDTO.getCompanyIdx());

        if (isCompanyCount == 0) {
            throw new CompanyException(ErrorCode.isCompanyNotFoundException);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Inquiry inquiry = modelMapper.map(inquiryInsertDTO, Inquiry.class);
        inquiry.setWriteIp(getUserIp.returnIP());
        inquiryMapper.insertInquiry(inquiry);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("inquiry", inquiryMapper.findOneInquiryByIdx(idx));
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();

        inquiryMapper.deleteInquiryByIdx(idx);
        return result;
    }


    /*
     ** 매니저 기능 시작
     */
    public ApiResponse managerLists(PageDTO pageDTO, long idx) {
        ResponseMap result = new ResponseMap();

        if (idx != getUser().getCompanyIdx()) {
            throw new AuthenticationException(ErrorCode.ForbiddenException);
        }

        result.setResponseData("inquiryList", inquiryMapper.findAllInquiryByCompany(pageDTO, idx));
        return result;
    }

    public ApiResponse managerWrite(InquiryDTO.InquiryInsertDTO inquiryInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isCompanyCount = companyMapper.isCompanyExist(inquiryInsertDTO.getCompanyIdx());

        if (isCompanyCount == 0) {
            throw new CompanyException(ErrorCode.isCompanyNotFoundException);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Inquiry inquiry = modelMapper.map(inquiryInsertDTO, Inquiry.class);
        inquiry.setWriteIp(getUserIp.returnIP());
        inquiryMapper.insertInquiry(inquiry);
        return result;
    }
}
