package com.intw.mentorapi.dao;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDetail {

    private long idx;
    private long commonFileIdx;
    private String fileName;
    private String filePath;
    private String fileExt;
}
