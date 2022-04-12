package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.RefreshToken;
import com.intw.mentorapi.dao.User;
import org.mapstruct.Mapper;

@Mapper
public interface AuthMapper {

    int isEmailExist(String email);
    int isPhoneExist(String phone);

    User findOneUserEmail(String email);
    void insertUser(User user);
    String findOneRefreshToken(long idx);
    void insertOrUpdateRefreshToken(RefreshToken refreshToken);
}
