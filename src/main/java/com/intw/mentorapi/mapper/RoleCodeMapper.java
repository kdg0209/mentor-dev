package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.RoleCode;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleCodeMapper {


    RoleCode isRoleExist(int code);

    List<RoleCode> findAllRoleCode();
    void inserRoleCode(RoleCode roleCode);
}
