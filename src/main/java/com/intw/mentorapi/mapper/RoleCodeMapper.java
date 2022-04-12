package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.RoleCode;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleCodeMapper {

    int isRoleExist(int idx);

    List<RoleCode> findAllRoleCode();
    void inserRoleCode(RoleCode roleCode);
}
