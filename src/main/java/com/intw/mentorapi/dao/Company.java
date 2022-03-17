package com.intw.mentorapi.dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Company {

    private long idx;
    private long userIdx;
    private String status;
    private String name;
    private String tel;
    private String address;
    private String category;
    private String ceo;
    private String employeeCount;
    private String corporationNumber;
    private String businessNumber;
    private String createAt;
    private String updateAt;
}
