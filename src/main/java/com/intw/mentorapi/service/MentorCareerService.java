package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.MentorCareer;
import com.intw.mentorapi.dto.mentorCareer.MentorCareerDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.MentorCareerException;
import com.intw.mentorapi.exception.customException.MentorException;
import com.intw.mentorapi.mapper.MentorCareerMapper;
import com.intw.mentorapi.mapper.MentorMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MentorCareerService {

    private final MentorMapper mentorMapper;
    private final MentorCareerMapper mentorCareerMapper;

    public ApiResponse write(MentorCareerDTO.MentorCareerInsertDTO mentorCareerInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorExist = mentorMapper.isMentorExist(mentorCareerInsertDTO.getMentorIdx());

        if (isMentorExist == 0) {
            throw new MentorException(ErrorCode.isMentorNotFoundException);
        }
        double annual = yearCalculation(mentorCareerInsertDTO.getStartAt(), mentorCareerInsertDTO.getEndAt());

        MentorCareer mentorCareer = MentorCareer.builder()
                        .mentorIdx(mentorCareerInsertDTO.getMentorIdx())
                        .status(mentorCareerInsertDTO.getStatus())
                        .company(mentorCareerInsertDTO.getCompany())
                        .department(mentorCareerInsertDTO.getDepartment())
                        .grade(mentorCareerInsertDTO.getGrade())
                        .annual(annual)
                        .startAt(mentorCareerInsertDTO.getStartAt())
                        .endAt(mentorCareerInsertDTO.getEndAt())
                        .build();

        mentorCareerMapper.insertMentorCareer(mentorCareer);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("mentorCareer", mentorCareerMapper.findOneMentorCareer(idx));
        return result;
    }

    public ApiResponse update(MentorCareerDTO.MentorCareerUpdateDTO mentorCareerUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isMentorCareerExist = mentorCareerMapper.isMentorCareerExist(mentorCareerUpdateDTO.getIdx());

        if (isMentorCareerExist == 0) {
            throw new MentorCareerException(ErrorCode.isMentorCareerNotFoundException);
        }

        double annual = yearCalculation(mentorCareerUpdateDTO.getStartAt(), mentorCareerUpdateDTO.getEndAt());

        MentorCareer mentorCareer = MentorCareer.builder()
                .idx(mentorCareerUpdateDTO.getIdx())
                .status(mentorCareerUpdateDTO.getStatus())
                .company(mentorCareerUpdateDTO.getCompany())
                .department(mentorCareerUpdateDTO.getDepartment())
                .grade(mentorCareerUpdateDTO.getGrade())
                .annual(annual)
                .startAt(mentorCareerUpdateDTO.getStartAt())
                .endAt(mentorCareerUpdateDTO.getEndAt())
                .build();

        mentorCareerMapper.updateMentorCareer(mentorCareer);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        mentorCareerMapper.deleteMentorCareer(idx);
        return result;
    }

    /**
     * 연차를 계산하는 메서드
     * @param startAt
     * @param endAt
     * @return
     */
    private double yearCalculation(String startAt, String endAt) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long diffMonth = 0;

        try {
            Date startDate = format.parse(startAt.replace("-", ""));
            Date endDate = format.parse(endAt.replace("-", ""));

            long baseDay = 24 * 60 * 60 * 1000; // 월
            long baseMonth = baseDay * 30;		// 월
            long diffDate = endDate.getTime() - startDate.getTime();

            diffMonth = diffDate / baseMonth;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MentorCareerException(ErrorCode.InvalidMentorCareerException);
        }

        double result = (double) diffMonth / 12;
        return cutDecimal(1, result);
    }

    /**
     * 소수값을 적당한 길이로 잘라 리턴
     * @param cutSize
     * @param value
     */
    private double cutDecimal(int cutSize, double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(cutSize);
        nf.setGroupingUsed(false);

        return Double.parseDouble(nf.format(value));
    }
}
