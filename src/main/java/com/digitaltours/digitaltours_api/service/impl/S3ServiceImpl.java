package com.digitaltours.digitaltours_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digitaltours.digitaltours_api.service.S3Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;
    
    @Autowired
    public S3ServiceImpl (S3Client s3Client){
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try{
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("digital-tours-img")
                .key(fileName)
                .contentType(contentType)
                .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            return "Archivo subido correctamente";
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}