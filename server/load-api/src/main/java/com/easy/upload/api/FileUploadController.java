package com.easy.upload.api;

import com.easy.upload.common.dto.FileListDto;
import com.easy.upload.common.exceptions.StorageFileNotFoundException;
import com.easy.upload.common.interfaces.services.UploadService;
import com.easy.upload.common.interfaces.services.UploadServiceByJson;
import com.easy.upload.common.interfaces.services.UploadServiceByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller contains one method for test task
 *
 */
@Controller
public class FileUploadController {

  @Autowired
  private UploadService uploadService;

  @Autowired
  private UploadServiceByUrl uploadServiceByUrl;

  @Autowired
  private UploadServiceByJson uploadServiceByJson;

  /**
   * Method allows upload list of files by multipart/form-data request
   * Method allows upload files by JSON in BASE64 request
   * Method allows upload file by url request
   * @param files  list of files by multipart/form-data
   * @param fileListDto JSON
   * @param fileUrl url
   * @param redirectAttributes need for html form
   * @return redirect path
   * @throws IOException
   */
  @PostMapping("/")
  public String handleFileUpload(@RequestParam(value = "files", required = false) MultipartFile[] files,
                                 @RequestPart(value = "json", required = false) FileListDto fileListDto,
                                 @RequestPart(value = "fileUrl", required = false) String fileUrl,
                                 RedirectAttributes redirectAttributes) throws IOException {

    String names = uploadService.upload(files);
    String namesByJson = uploadServiceByJson.upload(fileListDto);
    String nameByUrl = uploadServiceByUrl.upload(fileUrl);
    redirectAttributes.addFlashAttribute("message",
        "You successfully uploaded " + names + namesByJson + nameByUrl + "!");
    return "redirect:/";
  }

  /**
   * Handle exception StorageFileNotFoundException
   * @param exc
   * @return
   */
  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
  }
}