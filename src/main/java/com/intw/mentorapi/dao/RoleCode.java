package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleCode {

    private int idx;
    private int code;
    private String name;
}
