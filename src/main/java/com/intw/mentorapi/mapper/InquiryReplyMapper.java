package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.InquiryReply;
import com.intw.mentorapi.dto.inquiryReply.InquiryReplyListDTO;
import com.intw.mentorapi.status.RoleStatus;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InquiryReplyMapper {

    Integer isAccessible(long idx);

    List<InquiryReplyListDTO> findAllInquiryReply(long inquiryIdx, long companyIdx);
    void insertInquiryReply(InquiryReply inquiryReply);
    void updateInquiryReply(@Param("inquiryReply") InquiryReply inquiryReply, RoleStatus role);
    void deleteInquiryReply(long idx, RoleStatus role, long companyIdx);
    void deleteAllInquiryReplyByInquiry(long inquiryIdx, RoleStatus role, long companyIdx);
}
