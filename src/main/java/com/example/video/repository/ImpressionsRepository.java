package com.example.video.repository;

import com.example.video.entity.Impressions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpressionsRepository extends JpaRepository<Impressions, Long> {

    List<Impressions> findByFileId(Long fileId);


}
