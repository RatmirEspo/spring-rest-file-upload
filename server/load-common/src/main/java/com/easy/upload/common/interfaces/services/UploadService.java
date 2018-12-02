package com.easy.upload.common.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Interface for UploadService by list of MultipartFile
 */
public interface UploadService {

  String upload(MultipartFile[] fileList) throws IOException;
}
