package com.smartlocker.smartlockerbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartlocker.smartlockerbackend.model.LockerFile;

public interface LockerFileRepository extends JpaRepository<LockerFile, Long> {
    List<LockerFile> findByUserId(Long userId);

}
