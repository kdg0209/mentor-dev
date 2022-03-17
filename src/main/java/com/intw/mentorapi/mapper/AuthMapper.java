package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.RefreshToken;
import com.intw.mentorapi.dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    User findByEmail(String email);
    int isEmailExist(String email);
    int isPhoneExist(String phone);
    void insertUser(User user);
    String findRefreshTokenByIdx(long idx);
    void insertOrUpdateRefreshToken(RefreshToken refreshToken);
}
