package com.smartlocker.smartlockerbackend.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartlocker.smartlockerbackend.model.LockerFile;
import com.smartlocker.smartlockerbackend.model.User;
import com.smartlocker.smartlockerbackend.repository.LockerFileRepository;
import com.smartlocker.smartlockerbackend.repository.UserRepository;
@Service
public class FileServiceImpl implements FileService{
    
    @Autowired private LockerFileRepository lockerFileRepository;
    @Autowired private UserRepository userRepository;

    private final String uploadDir = "uploads/";

    @Override
    public LockerFile uploadFile(MultipartFile file, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new Exception("User not found");

        Files.createDirectories(Paths.get(uploadDir));
        String filePath = uploadDir + file.getOriginalFilename();
        Files.write(Paths.get(filePath), file.getBytes());

        LockerFile lockerFile = new LockerFile();
        lockerFile.setFilename(file.getOriginalFilename());
        lockerFile.setFilepath(filePath);
        lockerFile.setUploadedDate(LocalDateTime.now());
        lockerFile.setUser(user);

        return lockerFileRepository.save(lockerFile);
    }

    @Override
    public List<LockerFile> getFilesByUser(String username) {
        User user = userRepository.findByUsername(username);
        return lockerFileRepository.findByUserId(user.getId());
    }

    @Override
    public byte[] downloadFile(Long fileId) throws Exception {
        LockerFile lockerFile = lockerFileRepository.findById(fileId).orElseThrow(() -> new Exception("File not found"));
        return Files.readAllBytes(Paths.get(lockerFile.getFilepath()));
    }

    @Override
    public void deleteFile(Long fileId) throws Exception {
        LockerFile lockerFile = lockerFileRepository.findById(fileId).orElseThrow(() -> new Exception("File not found"));
        Files.deleteIfExists(Paths.get(lockerFile.getFilepath()));
        lockerFileRepository.deleteById(fileId);
    }

}
