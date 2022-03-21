package com.intw.mentorapi.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.FileUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    /**
     * s3에 파일 업로드하는 메서드
     * @param List<MultipartFile> files
     * @return List<Map> fileList
     */
    public List<Map> uploadFile(List<MultipartFile> files) {
        List<Map> fileList = new ArrayList<>();

        files.forEach(file -> {
            Map<String, String> uploadFiles = new LinkedHashMap();
            String originName = file.getOriginalFilename();
            String fileName = createFileName(file.getOriginalFilename());
            String extension = originName.substring(originName.lastIndexOf("."));

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try {
                InputStream inputStream = file.getInputStream();
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new FileUploadException(ErrorCode.FileUploadFailException);
            }

            uploadFiles.put("originName", originName);
            uploadFiles.put("filePath", fileName);
            uploadFiles.put("extension", extension);

            fileList.add(uploadFiles);
        });

        return fileList;
    }

    /**
     * 파일명을 받아 s3에 업로드된 파일 삭제하는 메서드
     * @param fileName
     */
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    /**
     * 파일 업로드 시 파일명을 UUID로 랜덤하게 생성하는 메서드
     * @param fileName
     * @return fileName
     */
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * 파일의 확장자 확인하는 메서드
     * @param fileName
     * @return String or Exception
     */
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new FileUploadException(ErrorCode.InvalidFileExtensionException);
        }
    }
}