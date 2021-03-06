package com.ningmeng.filesystem.controller;

import com.ningmeng.api.filesystem.FileSystemControllerApi;
import com.ningmeng.filesystem.service.FilesystemService;
import com.ningmeng.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/filesystem")
public class FilesystemController implements FileSystemControllerApi {

    @Autowired
    private FilesystemService filesystemService;


    @Override
    @RequestMapping("/upload")
    public UploadFileResult upload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "filetag", required = true) String filetag,
                                   @RequestParam(value = "businesskey", required = false)  String businesskey,
                                   @RequestParam(value = "metadata", required = false) String metadata) {
        return filesystemService.upload(file,filetag,businesskey,metadata);
    }
}
