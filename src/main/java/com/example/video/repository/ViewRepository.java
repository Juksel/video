package com.example.video.repository;

import com.example.video.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ViewRepository extends JpaRepository<View, Long> {

    List<View> findByFileId(Long fileId);
}
