package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.Company;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.company.CompanyDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.CompanyException;
import com.intw.mentorapi.exception.customException.UserException;
import com.intw.mentorapi.mapper.CompanyMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("companyList", companyMapper.findAllCompany(pageDTO));
        return result;
    }

    public ApiResponse write(CompanyDTO.CompanyInsertDTO companyInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isCorporationNumberCount = companyMapper.isCorporationNumberExist(companyInsertDTO.getCorporationNumber(), null);
        int isBusinessNumberCount = companyMapper.isBusinessNumberExist(companyInsertDTO.getBusinessNumber(), null);

        if (isCorporationNumberCount > 0) {
            throw new CompanyException(ErrorCode.isCorporationNumberExistException);
        }

        if (isBusinessNumberCount > 0) {
            throw new UserException(ErrorCode.isBusinessNumberExistException);
        }

        Company company = Company.builder()
                            .userIdx(companyInsertDTO.getUserIdx())
                            .status(companyInsertDTO.getStatus())
                            .name(companyInsertDTO.getName())
                            .tel(companyInsertDTO.getTel())
                            .email(companyInsertDTO.getEmail())
                            .address(companyInsertDTO.getAddress())
                            .category(companyInsertDTO.getCategory())
                            .ceo(companyInsertDTO.getCeo())
                            .employeeCount(companyInsertDTO.getEmployeeCount())
                            .corporationNumber(companyInsertDTO.getCorporationNumber())
                            .businessNumber(companyInsertDTO.getBusinessNumber())
                            .managerName(companyInsertDTO.getManagerName())
                            .managerEmail(companyInsertDTO.getManagerEmail())
                            .managerPhone(companyInsertDTO.getManagerPhone())
                            .createAt(companyInsertDTO.getCreateAt())
                            .build();
        companyMapper.insertCompany(company);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("company", companyMapper.findOneCompany(idx));
        return result;
    }

    public ApiResponse update(CompanyDTO.CompanyUpdateDTO companyUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isCompanyExist = companyMapper.isCompanyExist(companyUpdateDTO.getIdx());

        if (isCompanyExist == 0) {
            throw new CompanyException(ErrorCode.isCompanyNotFoundException);
        }

        Company company = Company.builder()
                            .idx(companyUpdateDTO.getIdx())
                            .status(companyUpdateDTO.getStatus())
                            .name(companyUpdateDTO.getName())
                            .tel(companyUpdateDTO.getTel())
                            .email(companyUpdateDTO.getEmail())
                            .address(companyUpdateDTO.getAddress())
                            .category(companyUpdateDTO.getCategory())
                            .ceo(companyUpdateDTO.getCeo())
                            .employeeCount(companyUpdateDTO.getEmployeeCount())
                            .totalTime(companyUpdateDTO.getTotalTime())
                            .totalUsedTime(companyUpdateDTO.getTotalUsedTime())
                            .availableTime(companyUpdateDTO.getAvailableTime())
                            .serviceStartAt(companyUpdateDTO.getServiceStartAt())
                            .serviceEndAt(companyUpdateDTO.getServiceEndAt())
                            .managerName(companyUpdateDTO.getManagerName())
                            .managerEmail(companyUpdateDTO.getManagerEmail())
                            .managerPhone(companyUpdateDTO.getManagerPhone())
                            .createAt(companyUpdateDTO.getCreateAt())
                            .build();

        companyMapper.updateCompany(company);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        companyMapper.deleteCompany(idx);
        return result;
    }
}
