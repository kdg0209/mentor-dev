package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Company;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.company.CompanyListDTO;
import com.intw.mentorapi.dto.company.CompanyViewDTO;
import org.mapstruct.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {

    int isCorporationNumberExist(String corporationNumber, Long idx);
    int isBusinessNumberExist(String businessNumber, Long idx);

    List<CompanyListDTO> findAllCompany(@Param("pageDTO") PageDTO pageDTO);
    void insertCompany(Company company);
    CompanyViewDTO findOneCompanyByIdx(long idx);
    void updateCompanyByIdx(Company company);
    void deleteCompanyByIdx(long idx);
}
