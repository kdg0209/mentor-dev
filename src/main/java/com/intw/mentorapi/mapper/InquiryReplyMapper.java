package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.InquiryReply;
import org.mapstruct.Mapper;

@Mapper
public interface InquiryReplyMapper {

    void insertInquiryReply(InquiryReply inquiryReply);
    void updateInquiryReply(InquiryReply inquiryReply);
    void deleteInquiryReplyByIdx(long idx);
}
