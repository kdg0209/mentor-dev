package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.User;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.AuthenticationException;
import com.intw.mentorapi.mapper.AuthMapper;
import com.intw.mentorapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AuthMapper authMapper;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authMapper.findByEmail(username);

        if(user == null){
            throw new AuthenticationException(ErrorCode.UsernameOrPasswordNotFoundException);
        }

        userMapper.updateVisitAtByUserEmail(username);
        return user;
    }
}
