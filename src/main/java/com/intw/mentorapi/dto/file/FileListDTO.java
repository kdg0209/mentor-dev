package com.intw.mentorapi.dto.file;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileListDTO {

    private String fileName;
    private String filePath;
    private String targetType;

}
