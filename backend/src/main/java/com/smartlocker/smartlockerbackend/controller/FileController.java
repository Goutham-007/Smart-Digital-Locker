package com.smartlocker.smartlockerbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartlocker.smartlockerbackend.model.LockerFile;
import com.smartlocker.smartlockerbackend.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
     @Autowired private FileService fileService;

    @PostMapping("/upload")
    public LockerFile upload(@RequestParam("file") MultipartFile file, Authentication auth) throws Exception {
        return fileService.uploadFile(file, auth.getName());
    }

    @GetMapping
    public List<LockerFile> getFiles(Authentication auth) {
        return fileService.getFilesByUser(auth.getName());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws Exception {
        byte[] fileData = fileService.downloadFile(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.pdf")
            .body(fileData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        fileService.deleteFile(id);
        return ResponseEntity.ok("File deleted successfully");
    }

}
