package com.intw.mentorapi.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPassword {

    public String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
