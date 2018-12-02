package com.easy.upload.services;

import com.easy.upload.common.interfaces.services.StorageProperties;
import com.easy.upload.common.interfaces.services.UploadServiceByUrl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for UploadService
 */
public class UploadServiceByUrlTests {

  private StorageProperties properties = new StoragePropertiesImpl();
  private FileSystemStorageService serviceStorage;
  private UploadServiceByUrl service;
  private RestTemplate restTemplate;

  @Before
  public void init() {
    properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
    serviceStorage = new FileSystemStorageService(properties);
    serviceStorage.init();
    restTemplate = new RestTemplate();
    service = new UploadServiceByUrlImpl(serviceStorage, restTemplate);
  }

  @Test
  public void uploadTest() throws IOException {

    service.upload("https://www.google.ru/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");

    assertThat(serviceStorage.loadAll().count()).isEqualTo(2);
  }
}
