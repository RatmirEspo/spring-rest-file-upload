package com.easy.upload.services;

import com.easy.upload.common.dto.FileDto;
import com.easy.upload.common.dto.FileListDto;
import com.easy.upload.common.interfaces.services.StorageService;
import com.easy.upload.common.interfaces.services.UploadServiceByJson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * UploadService by JSON
 */
@Service
public class UploadServiceByJsonImpl implements UploadServiceByJson {

  StorageService storageService;

  @Autowired
  public UploadServiceByJsonImpl(StorageService storageService) {
    this.storageService = storageService;
  }

  @Override
  public String upload(FileListDto fileListDto) throws IOException {
    if (fileListDto == null) return "";
    StringBuilder names = new StringBuilder();
    for (FileDto fileDto : fileListDto.getFiles()) {
      byte[] imageBytes = Base64.decodeBase64(fileDto.getBody());
      storageService.store(imageBytes, fileDto.getFilename());
      storageService.createThumbnail(fileDto.getFilename());
      names.append(fileDto.getFilename()).append(";");
    }

    return names.toString();
  }
}
