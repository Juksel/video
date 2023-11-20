package com.example.video.service;


import com.example.video.entity.File;
import com.example.video.repository.FileContentRepository;
import com.example.video.repository.FileContentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileContentStore fileContentStoreRepository;
    @Autowired
    private FileContentRepository fileContentRepository;
    @Autowired
    private FileContentStore contentStore;
    private File fileUpload;
    public ResponseEntity<?> setContent( MultipartFile multipartFile, String mainActor)
            throws IOException {
        fileUpload = new File();
        fileUpload.setContentMimeType(multipartFile.getContentType());
        fileUpload.setName(multipartFile.getName());
        fileUpload.setContentLength(multipartFile.getBytes().length);
        fileUpload.setMainActor(mainActor);
        contentStore.setContent(fileUpload, multipartFile.getInputStream());
        // save updated content-related info
        fileContentRepository.save(fileUpload);

        return new ResponseEntity<Object>(HttpStatus.OK);


    }

    public ResponseEntity<?> setUpdateContent(Long id, MultipartFile multipartFile)
            throws IOException {

        Optional<File> f = fileContentRepository.findById(id);
        if (f.isPresent()) {
            f.get().setContentMimeType(multipartFile.getContentType());

            contentStore.setContent(f.get(), multipartFile.getInputStream());
            fileUpload.setName(multipartFile.getName());
            fileUpload.setContentLength(multipartFile.getBytes().length);
            // save updated content-related info
            fileContentRepository.save(f.get());

            return new ResponseEntity<Object>(HttpStatus.OK);
        }

        //   filesRepo.save()
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getContent(Long id) {

        Optional<File> f = fileContentRepository.findById(id);
        if (f.isPresent()) {
            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f.get()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.get().getContentLength());
            headers.set("Content-Type", f.get().getContentMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<?> deleteContent(Long id) {
        Optional<File> f = fileContentRepository.findById(id);
        if(f.isPresent()) {
            contentStore.unsetContent(f.get());
            fileContentRepository.delete(f.get());
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

}
