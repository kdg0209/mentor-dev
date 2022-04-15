package com.intw.mentorapi.service;

import com.intw.mentorapi.common.HashPassword;
import com.intw.mentorapi.dao.Company;
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
import com.intw.mentorapi.status.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final CompanyMapper companyMapper;
    private final RoleCodeMapper roleCodeMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("userList", userMapper.findAllUser(pageDTO));
        return result;
    }

    public ApiResponse write(UserDTO.UserInsertDTO userDTO) {
        ResponseMap result = new ResponseMap();

        int isEmailCount = userMapper.isEmailExist(userDTO.getEmail());
        int isPhoneCount = userMapper.isPhoneExist(userDTO.getPhone(), null);
        int isRoleExist = roleCodeMapper.isRoleExist(userDTO.getRoleCodeIdx());

        if (isRoleExist == 0) {
            throw new RoleCodeException(ErrorCode.isRoleNotFoundException);
        }

        if (isEmailCount > 0) {
            throw new UserException(ErrorCode.isEmailExistException);
        }

        if (isPhoneCount > 0) {
            throw new UserException(ErrorCode.isPhoneExistException);
        }

        userDTO.setPassword(new HashPassword().hashPassword(userDTO.getPassword()));
        User user = User.builder()
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .role(RoleStatus.valueOf(userDTO.getRole()))
                    .roleCodeIdx(userDTO.getRoleCodeIdx())
                    .status(userDTO.getStatus())
                    .name(userDTO.getName())
                    .phone(userDTO.getPhone())
                    .gender(userDTO.getGender())
                    .isAgreement(userDTO.getIsAgreement())
                    .build();
        userMapper.insertUser(user);

        CompanyDTO.CompanyInsertDTO companyDTO = userDTO.getCompanyDTO();
        Company company = Company.builder()
                            .userIdx(user.getIdx())
                            .status(companyDTO.getStatus())
                            .name(companyDTO.getName())
                            .tel(companyDTO.getTel())
                            .email(companyDTO.getEmail())
                            .address(companyDTO.getAddress())
                            .category(companyDTO.getCategory())
                            .ceo(companyDTO.getCeo())
                            .employeeCount(companyDTO.getEmployeeCount())
                            .businessNumber(companyDTO.getBusinessNumber())
                            .managerName(companyDTO.getManagerName())
                            .managerEmail(companyDTO.getManagerEmail())
                            .managerPhone(companyDTO.getManagerPhone())
                            .createAt(companyDTO.getCreateAt())
                            .build();

        companyMapper.insertCompany(company);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("user", userMapper.findOneUser(idx));
        return result;
    }

    public ApiResponse update(UserDTO.UserUpdateDTO userDTO) {
        ResponseMap result = new ResponseMap();

        int isPhoneCount = userMapper.isPhoneExist(userDTO.getPhone(), userDTO.getIdx());
        int isRoleExist = roleCodeMapper.isRoleExist(userDTO.getRoleCodeIdx());

        if (isRoleExist == 0) {
            throw new RoleCodeException(ErrorCode.isRoleNotFoundException);
        }

        if (isPhoneCount > 0) {
            throw new UserException(ErrorCode.isPhoneExistException);
        }

        if (userDTO.getPassword() != null) {
            userDTO.setPassword(new HashPassword().hashPassword(userDTO.getPassword()));
        }

        User user = User.builder()
                    .idx(userDTO.getIdx())
                    .password(userDTO.getPassword())
                    .role(RoleStatus.valueOf(userDTO.getRole()))
                    .roleCodeIdx(userDTO.getRoleCodeIdx())
                    .status(userDTO.getStatus())
                    .name(userDTO.getName())
                    .phone(userDTO.getPhone())
                    .gender(userDTO.getGender())
                    .isAgreement(userDTO.getIsAgreement())
                    .build();

        userMapper.updateUser(user);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        userMapper.deleteUser(idx);
        return result;
    }
}
