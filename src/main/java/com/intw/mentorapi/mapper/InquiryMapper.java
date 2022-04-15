package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Inquiry;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.inquiry.InquiryListDTO;
import com.intw.mentorapi.dto.inquiry.InquiryViewDTO;
import com.intw.mentorapi.status.RoleStatus;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    int isInquiryExist(long idx);

    /**
     * 최종 관리자가 사용하는 메서드
     */
    List<InquiryListDTO> findAllInquiry(@Param("pageDTO") PageDTO pageDTO, String role, long companyIdx);
    InquiryViewDTO findOneInquiry(long idx, String role, long companyIdx);
    void updateStatusByInquiryReply(long idx, String role);
    void deleteInquiry(long idx, String role, long companyIdx);

    /**
     * 기업 관리자가 사용하는 메서드
     */
    void insertInquiryByCompany(Inquiry inquiry);
    void updateInquiryByCompany(Inquiry inquiry);
}
