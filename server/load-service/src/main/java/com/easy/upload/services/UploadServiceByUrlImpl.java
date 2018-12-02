package com.easy.upload.services;

import com.easy.upload.common.interfaces.services.StorageService;
import com.easy.upload.common.interfaces.services.UploadServiceByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

/**
 * Interface for UploadService by URL file
 */
@Service
public class UploadServiceByUrlImpl implements UploadServiceByUrl {

  StorageService storageService;
  RestTemplate restTemplate;

  @Autowired
  public UploadServiceByUrlImpl(StorageService storageService, RestTemplate restTemplate) {
    this.storageService = storageService;
    this.restTemplate = restTemplate;
  }

  @Override
  public String upload(String fileUrl) throws IOException {
    if (fileUrl == null) return "";
    StringBuilder names = new StringBuilder();

    byte[] imageBytes = restTemplate.getForObject(fileUrl, byte[].class);
    String extension = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
    String filename = String.format("%s.%s", UUID.randomUUID().toString(), extension);
    storageService.store(imageBytes, filename);
    storageService.createThumbnail(filename);
    names.append(filename).append(";");


    return names.toString();
  }
}
