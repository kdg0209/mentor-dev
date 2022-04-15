package com.intw.mentorapi.dao;

import com.intw.mentorapi.status.RoleStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private long idx;
    private RoleStatus role;
    private int roleCode;
    private int roleCodeIdx;
    private long companyIdx;
    private String status;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private String isAgreement;
    private LocalDate joinAt;
    private LocalDateTime writeAt;
    private LocalDateTime updateAt;
    private LocalDateTime visitAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(this.role.toString()));
        return collection;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
