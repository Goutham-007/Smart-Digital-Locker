package com.smartlocker.smartlockerbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartlocker.smartlockerbackend.model.LockerFile;
@Service
public interface FileService {
     LockerFile uploadFile(MultipartFile file, String username) throws Exception;
    List<LockerFile> getFilesByUser(String username);
    byte[] downloadFile(Long fileId) throws Exception;
    void deleteFile(Long fileId) throws Exception;

}
