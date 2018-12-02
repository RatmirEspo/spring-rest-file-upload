package com.easy.upload.services;

import com.easy.upload.common.interfaces.services.StorageService;
import com.easy.upload.common.interfaces.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UploadService by list of MultipartFile
 */
@Service
public class UploadServiceImpl implements UploadService {

  StorageService storageService;

  @Autowired
  public UploadServiceImpl(StorageService storageService) {
    this.storageService = storageService;
  }

  @Override
  public String upload(MultipartFile[] files) throws IOException {
    if (files == null) return "";
    List<MultipartFile> fileList = Arrays.asList(files);
    for (MultipartFile file : fileList) {
      storageService.store(file);
      storageService.createThumbnail(file.getOriginalFilename());
    }

    return fileList.stream()
        .map(x -> x.getOriginalFilename())
        .collect(Collectors.joining(";"));
  }
}
