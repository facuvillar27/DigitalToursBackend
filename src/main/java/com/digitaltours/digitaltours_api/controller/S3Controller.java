package com.digitaltours.digitaltours_api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitaltours.digitaltours_api.service.S3Service;

import io.swagger.v3.oas.annotations.Operation;

@Validated
@CrossOrigin
@RestController
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @Operation(summary = "Cargar imagen a S3", description = "Carga una imagen a S3")
    @PostMapping("/v1/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return s3Service.uploadFile(file);
    }

}
