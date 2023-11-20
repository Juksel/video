package com.example.video.service;

import com.example.video.entity.View;
import com.example.video.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ViewService {

    @Autowired
    ViewRepository viewRepository;


    View view;

    public void save(Long fileId) {

        if(viewRepository.findByFileId(fileId).isEmpty()) {
            view = new View();
            view.setSum(1L);
            view.setFileId(fileId);
            viewRepository.save(view);
        } else {
            view = viewRepository.findByFileId(fileId).get(0);
            view.setSum(view.getSum()+1);
            viewRepository.save(view);
        }
    }

    public Long getViews(Long fileId) {
        return viewRepository.findByFileId(fileId).stream().count();
    }

}
