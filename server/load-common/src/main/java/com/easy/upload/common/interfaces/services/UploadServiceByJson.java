package com.easy.upload.common.interfaces.services;

import com.easy.upload.common.dto.FileListDto;

import java.io.IOException;

/**
 * Interface for UploadService by JSON
 */
public interface UploadServiceByJson {
  String upload(FileListDto fileListDto) throws IOException;
}
