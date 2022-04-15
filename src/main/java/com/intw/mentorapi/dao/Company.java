package com.intw.mentorapi.dao;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private long idx;
    private long userIdx;
    private String status;
    private String name;
    private String email;
    private String tel;
    private String address;
    private String category;
    private String ceo;
    private String employeeCount;
    private String corporationNumber;
    private String businessNumber;
    private float totalTime;
    private float totalUsedTime;
    private float availableTime;
    private LocalDate serviceStartAt;
    private LocalDate serviceEndAt;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    private LocalDate createAt;
    private LocalDateTime writeAt;
    private LocalDateTime updateAt;
}
