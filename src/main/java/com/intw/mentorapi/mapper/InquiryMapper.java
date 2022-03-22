package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Inquiry;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.inquiry.InquiryListDTO;
import com.intw.mentorapi.dto.inquiry.InquiryViewDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    int isInquiryExist(long idx);

    List<InquiryListDTO> findAllInquiry(@Param("pageDTO") PageDTO pageDTO);
    void insertInquiry(Inquiry inquiry);
    InquiryViewDTO findOneInquiryByIdx(long idx);
    void updateStatusByInquiryReply(long idx);
    void deleteInquiryByIdx(long idx);

    List<InquiryListDTO> findAllInquiryByCompany(@Param("pageDTO") PageDTO pageDTO, long idx);
}
