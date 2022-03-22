package com.intw.mentorapi.service;

import com.intw.mentorapi.common.HashPassword;
import com.intw.mentorapi.dao.Company;
import com.intw.mentorapi.dao.RoleCode;
import com.intw.mentorapi.dao.User;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.company.CompanyDTO;
import com.intw.mentorapi.dto.user.UserDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.RoleCodeException;
import com.intw.mentorapi.exception.customException.UserException;
import com.intw.mentorapi.mapper.CompanyMapper;
import com.intw.mentorapi.mapper.RoleCodeMapper;
import com.intw.mentorapi.mapper.UserMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final CompanyMapper companyMapper;
    private final RoleCodeMapper roleCodeMapper;
    private final ModelMapper modelMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("userList", userMapper.findAllUser(pageDTO));
        return result;
    }

    public ApiResponse write(UserDTO.UserInsertDTO userDTO) {
        ResponseMap result = new ResponseMap();

        int isEmailCount = userMapper.isEmailExist(userDTO.getEmail());
        int isPhoneCount = userMapper.isPhoneExist(userDTO.getPhone(), null);
        RoleCode isRoleExist = roleCodeMapper.isRoleExist(userDTO.getRoleCodeIdx());

        if (isRoleExist == null) {
            throw new RoleCodeException(ErrorCode.isRoleNotFoundException);
        }

        if (isEmailCount > 0) {
            throw new UserException(ErrorCode.isEmailExistException);
        }

        if (isPhoneCount > 0) {
            throw new UserException(ErrorCode.isPhoneExistException);
        }

        userDTO.setPassword(new HashPassword().hashPassword(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        userMapper.insertUser(user);

        CompanyDTO.CompanyInsertDTO companyDTO = userDTO.getCompanyDTO();
        companyDTO.setUserIdx(user.getIdx());
        Company company = modelMapper.map(companyDTO, Company.class);
        companyMapper.insertCompany(company);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("user", userMapper.findOneUserByIdx(idx));
        return result;
    }

    public ApiResponse update(UserDTO.UserUpdateDTO userDTO) {
        ResponseMap result = new ResponseMap();

        int isPhoneCount = userMapper.isPhoneExist(userDTO.getPhone(), userDTO.getIdx());
        RoleCode isRoleExist = roleCodeMapper.isRoleExist(userDTO.getRoleCodeIdx());

        if (isRoleExist == null) {
            throw new RoleCodeException(ErrorCode.isRoleNotFoundException);
        }

        if (isPhoneCount > 0) {
            throw new UserException(ErrorCode.isPhoneExistException);
        }

        if (userDTO.getPassword() != null) {
            userDTO.setPassword(new HashPassword().hashPassword(userDTO.getPassword()));
        }

        User user = modelMapper.map(userDTO, User.class);
        userMapper.updateUserByIdx(user);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        userMapper.deleteUserByIdx(idx);
        return result;
    }
}
