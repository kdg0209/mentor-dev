package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Company;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {

    void insertCompany(@Param("Company") Company company, long userIdx);
}
