package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.MentorCertificate;
import org.mapstruct.Mapper;

@Mapper
public interface MentorCertificateMapper {

    int isMentorCertificateExist(long idx);

    MentorCertificate findOneMentorCertificate(long idx);
    void insertMentorCertificate(MentorCertificate mentorCertificate);
    void updateMentorCertificate(MentorCertificate mentorCertificate);
    void deleteMentorCertificate(long idx);
}
