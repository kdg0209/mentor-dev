package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.MentorCertificate;
import com.intw.mentorapi.dto.mentorCertificate.MentorCertificateDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.MentorCertificateException;
import com.intw.mentorapi.exception.customException.MentorException;
import com.intw.mentorapi.mapper.MentorCertificateMapper;
import com.intw.mentorapi.mapper.MentorMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorCertificateService {

    private final MentorMapper mentorMapper;
    private final MentorCertificateMapper mentorCertificateMapper;

    public ApiResponse write(MentorCertificateDTO.MentorCertificateInsertDTO mentorCertificateInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorExist = mentorMapper.isMentorExist(mentorCertificateInsertDTO.getMentorIdx());

        if (isMentorExist == 0) {
            throw new MentorException(ErrorCode.isMentorNotFoundException);
        }

        MentorCertificate mentorCertificate = MentorCertificate.builder()
                .mentorIdx(mentorCertificateInsertDTO.getMentorIdx())
                .name(mentorCertificateInsertDTO.getName())
                .contents(mentorCertificateInsertDTO.getContents())
                .acceptanceOrganization(mentorCertificateInsertDTO.getAcceptanceOrganization())
                .expirationAt(mentorCertificateInsertDTO.getExpirationAt())
                .build();

        mentorCertificateMapper.insertMentorCertificate(mentorCertificate);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();

        return result;
    }

    public ApiResponse update(MentorCertificateDTO.MentorCertificateUpdateDTO mentorCertificateUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorCertificateExist = mentorCertificateMapper.isMentorCertificateExist(mentorCertificateUpdateDTO.getIdx());

        if (isMentorCertificateExist > 0) {
            throw new MentorCertificateException(ErrorCode.isMentorCertificateNotFoundException);
        }

        MentorCertificate mentorCertificate = MentorCertificate.builder()
                .idx(mentorCertificateUpdateDTO.getIdx())
                .name(mentorCertificateUpdateDTO.getName())
                .contents(mentorCertificateUpdateDTO.getContents())
                .acceptanceOrganization(mentorCertificateUpdateDTO.getAcceptanceOrganization())
                .expirationAt(mentorCertificateUpdateDTO.getExpirationAt())
                .build();


        mentorCertificateMapper.updateMentorCertificate(mentorCertificate);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();

        mentorCertificateMapper.deleteMentorCertificate(idx);
        return result;
    }
}
