package com.example.video.controller;


import com.example.video.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    ViewService viewService;

    @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
    public Long getVideos(@PathVariable("fileId") Long id) {
        return viewService.getViews(id);
    }


}
