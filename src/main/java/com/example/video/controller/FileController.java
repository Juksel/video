package com.example.video.controller;


import com.example.video.service.FileService;
import com.example.video.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/example")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/files/{actor}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setContent(@PathVariable("actor") String actor, @RequestParam(value = "file", required =
            false) MultipartFile file)
            throws IOException {
        return fileService.setContent(file,actor);
    }


    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setUpdateContent(@PathVariable("fileId") Long id, @RequestParam(value = "file", required =
            false) MultipartFile file)
            throws IOException {
       return fileService.setUpdateContent(id, file);

    }


    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("fileId") Long id) {
        viewService.save(id);
        return fileService.getContent(id);
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteContent(@PathVariable("fileId") Long id) {

        return fileService.deleteContent(id);
    }

}
