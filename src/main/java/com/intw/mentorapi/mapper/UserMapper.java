package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.User;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.user.UserListDTO;
import com.intw.mentorapi.dto.user.UserViewDTO;
import org.mapstruct.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int isEmailExist(String email);
    int isPhoneExist(String phone, Long idx);
    void updateVisitAtByUserEmail(String email);

    List<UserListDTO> findAllUser(@Param("pageDTO") PageDTO pageDTO);
    UserViewDTO findOneUser(long idx);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(long idx);
}
