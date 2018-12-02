package com.easy.upload.common.interfaces.services;

import java.io.IOException;
import java.util.List;

/**
 * Interface for UploadService by URL file
 */
public interface UploadServiceByUrl {
  String upload(String fileList) throws IOException;
}
