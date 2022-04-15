package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.RoleCode;
import com.intw.mentorapi.dto.roleCode.RoleCodeDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.RoleCodeException;
import com.intw.mentorapi.mapper.RoleCodeMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleCodeService {

    private final RoleCodeMapper roleCodeMapper;

    public ApiResponse lists() {
        ResponseMap result = new ResponseMap();
        result.setResponseData("roleCodeList", roleCodeMapper.findAllRoleCode());
        return result;
    }

    public ApiResponse write(RoleCodeDTO params) {
        ResponseMap result = new ResponseMap();

        int isRoleExist = roleCodeMapper.isRoleExist(params.getCode());

        if (isRoleExist > 0) {
            throw new RoleCodeException(ErrorCode.isRoleExistException);
        }

        RoleCode role = RoleCode.builder()
                        .name(params.getName())
                        .build();
        roleCodeMapper.inserRoleCode(role);
        return result;
    }

}
