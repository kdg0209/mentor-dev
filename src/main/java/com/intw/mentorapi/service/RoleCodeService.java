package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.RoleCode;
import com.intw.mentorapi.dto.roleCode.RoleCodeDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.RoleCodeException;
import com.intw.mentorapi.mapper.RoleCodeMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleCodeService {

    private final RoleCodeMapper roleCodeMapper;
    private final ModelMapper modelMapper;

    public ApiResponse lists() {
        ResponseMap result = new ResponseMap();
        result.setResponseData("roleCodeList", roleCodeMapper.findAllRoleCode());
        return result;
    }

    public ApiResponse write(RoleCodeDTO roleCodeDTO) {
        ResponseMap result = new ResponseMap();

        RoleCode isRoleExist = roleCodeMapper.isRoleExist(roleCodeDTO.getCode());

        if (isRoleExist != null) {
            throw new RoleCodeException(ErrorCode.isRoleExistException);
        }

        RoleCode role = modelMapper.map(roleCodeDTO, RoleCode.class);
        roleCodeMapper.inserRoleCode(role);
        return result;
    }

}
