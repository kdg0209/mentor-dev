package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAllUser();
    int isEmailExist(String email);
    int isPhoneExist(String phone);
    void insertUser(User user);
    void updateVisitAtByUserEmail(String email);
}
