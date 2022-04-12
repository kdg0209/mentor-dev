package com.intw.mentorapi.dto.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class FileDTO {

    List<MultipartFile> files;
    private String targetType;
}
