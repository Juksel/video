package com.example.video.controller;

import com.example.video.entity.Impressions;
import com.example.video.service.ImpressionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ImpressionsController {

    @Autowired
    ImpressionsService impressionsService;

    @RequestMapping(value = "/impressions/{fileId}", method = RequestMethod.GET)
    public Integer getImpression(@PathVariable("fileId") Long id) {
        return impressionsService.getImpressions(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void saveImpression(@RequestBody Impressions impressions) {

        impressionsService.saveImpressions(impressions);
    }

}
