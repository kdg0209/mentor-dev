package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.File;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper {

    File findAllFile (long fkIdx, String tables);
    void insertFile(File file);
    void deleteCommonFile(long fkIdx);
}
