package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.FileDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FileDetailMapper {

    List<FileDetail> findAllFileDetail (long fkIdx);
    void insertFileDetail(List<FileDetail> fileDetails);
    void deleteCommonFileDetailByCommonFile(long fkIdx);
}
