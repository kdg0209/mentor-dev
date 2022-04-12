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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final ModelMapper modelMapper;

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

        Company company = modelMapper.map(companyInsertDTO, Company.class);
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
        int isCorporationNumberExist = companyMapper.isCorporationNumberExist(companyUpdateDTO.getCorporationNumber(), companyUpdateDTO.getIdx());
        int isBusinessNumberExist = companyMapper.isBusinessNumberExist(companyUpdateDTO.getBusinessNumber(), companyUpdateDTO.getIdx());

        if (isCompanyExist > 0) {
            throw new CompanyException(ErrorCode.isCompanyNotFoundException);
        }

        if (isCorporationNumberExist > 0) {
            throw new CompanyException(ErrorCode.isCorporationNumberExistException);
        }

        if (isBusinessNumberExist > 0) {
            throw new UserException(ErrorCode.isBusinessNumberExistException);
        }

        Company company = modelMapper.map(companyUpdateDTO, Company.class);
        companyMapper.updateCompany(company);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        companyMapper.deleteCompany(idx);
        return result;
    }
}
