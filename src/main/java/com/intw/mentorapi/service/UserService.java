package com.intw.mentorapi.service;

import com.intw.mentorapi.common.HashPassword;
import com.intw.mentorapi.dao.Company;
import com.intw.mentorapi.dao.User;
import com.intw.mentorapi.dto.user.UserDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.AuthException;
import com.intw.mentorapi.mapper.CompanyMapper;
import com.intw.mentorapi.mapper.UserMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final CompanyMapper companyMapper;
    private final ModelMapper modelMapper;

    public ApiResponse lists() {
        ResponseMap result = new ResponseMap();

        List<UserDTO> userList = userMapper.findAllUser().stream()
                .map(item -> modelMapper.map(item, UserDTO.class)).collect(Collectors.toList());

        result.setResponseData("userList", userList);
        return result;
    }

    public ApiResponse write(UserDTO.UserInsertDTO userDTO) {
        ResponseMap result = new ResponseMap();

        int isEmailCount = userMapper.isEmailExist(userDTO.getEmail());
        int isPhoneCount = userMapper.isPhoneExist(userDTO.getPhone());

        if (isEmailCount > 0) {
            throw new AuthException(ErrorCode.isEmailExistException);
        }

        if (isPhoneCount > 0) {
            throw new AuthException(ErrorCode.isPhoneExistException);
        }

        userDTO.setPassword(new HashPassword().hashPassword(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        Company company = modelMapper.map(userDTO.getCompanyDTO(), Company.class);

        userMapper.insertUser(user);
        companyMapper.insertCompany(company, user.getIdx());
        return result;
    }

}
