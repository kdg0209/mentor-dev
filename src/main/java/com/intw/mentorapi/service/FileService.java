package com.intw.mentorapi.service;

import com.intw.mentorapi.common.S3Uploader;
import com.intw.mentorapi.dao.File;
import com.intw.mentorapi.dao.FileDetail;
import com.intw.mentorapi.mapper.FileDetailMapper;
import com.intw.mentorapi.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final FileDetailMapper fileDetailMapper;
    private final S3Uploader s3Uploader;

    /**
     * s3에 업로드하는 메서드
     * @param files : 업로드할 이미지
     * @param targetType : 첨부파일의 타입
     * @param tables : 관련된 테이블 명칭
     * @param fkIdx : 외래키
     */
    @Transactional(rollbackFor = Exception.class)
    public void fileUpload(List<MultipartFile> files, String targetType, String tables, long fkIdx) {
        List<Map> uploadedFiles = s3Uploader.uploadFile(files);
        List<FileDetail> fileDetails = new ArrayList<>();

        File file = File.builder()
                .tables(tables)
                .fkIdx(fkIdx)
                .targetType(targetType)
                .build();

        fileMapper.insertFile(file);

        uploadedFiles.forEach(item -> {
            FileDetail fileDetail = FileDetail.builder()
                    .commonFileIdx(file.getIdx())
                    .fileName(item.get("originName").toString())
                    .filePath(item.get("filePath").toString())
                    .fileExt(item.get("extension").toString())
                    .build();
            fileDetails.add(fileDetail);
        });

        fileDetailMapper.insertFileDetail(fileDetails);
    }

    public void fileUpdate(List<MultipartFile> files, String tables, long fkIdx){
        File file = fileMapper.findAllFile(fkIdx, tables);
        List<Map> uploadedFiles = s3Uploader.uploadFile(files);
        List<FileDetail> fileDetails = new ArrayList<>();

        uploadedFiles.forEach(item -> {
            FileDetail fileDetail = FileDetail.builder()
                    .commonFileIdx(file.getIdx())
                    .fileName(item.get("originName").toString())
                    .filePath(item.get("filePath").toString())
                    .fileExt(item.get("extension").toString())
                    .build();

            fileDetails.add(fileDetail);
        });

        fileDetailMapper.insertFileDetail(fileDetails);
    }

    /**
     * s3에 업로드된 이미지 삭제 및 데이터 삭제
     * @param fkIdx 외래키
     * @param tables 관련된 테이블 명칭
     */
    @Transactional
    public void fileDelete(long fkIdx, String tables) {
        File file = fileMapper.findAllFile(fkIdx, tables);
        List<FileDetail> fileDetails = fileDetailMapper.findAllFileDetail(file.getIdx());

        for(FileDetail item : fileDetails) {
            s3Uploader.deleteFile(item.getFilePath());
        }

        fileDetailMapper.deleteCommonFileDetailByCommonFile(file.getIdx());
        fileMapper.deleteCommonFile(fkIdx);
    }
}
