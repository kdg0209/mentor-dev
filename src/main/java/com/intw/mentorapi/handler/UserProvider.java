package com.intw.mentorapi.handler;

import com.intw.mentorapi.dao.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserProvider {

    public User getUser() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (authentication instanceof User) {
            user = (User) authentication;
        }
        return user;
    }
}
