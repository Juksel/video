package com.example.video.repository;

import com.example.video.entity.File;
import org.springframework.content.commons.store.ContentStore;

public interface FileContentStore extends ContentStore<File, String> {

}
