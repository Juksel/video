package com.example.video.repository;

import com.example.video.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="files", collectionResourceRel="files")
public interface FileContentRepository extends JpaRepository<File, Long> {

}

