package com.example.video.service;


import com.example.video.entity.Impressions;
import com.example.video.repository.ImpressionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpressionsService {

    @Autowired
    ImpressionsRepository impressionsRepository;

    public Integer getImpressions(Long fileId) {
        List<Impressions> impressionsList = impressionsRepository.findByFileId(fileId);
        if(!impressionsList.isEmpty()) {
            return impressionsList.stream().mapToInt(o -> o.getImpressions()).sum() / impressionsList.size();
        }
        return 0;
    }

    public void saveImpressions(Impressions impressions) {
        impressionsRepository.save(impressions);
    }

}
