package com.example.video.service;

import com.example.video.entity.File;
import com.example.video.repository.FileContentRepository;
import com.example.video.repository.FileContentStore;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HexFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @InjectMocks
    FileService fileService;

    @Mock
    MultipartFile multipartFile;

    @Mock
    private FileContentStore contentStore;

    @Mock
    private FileUpload fileUpload;

    @Mock
    private File file;

    @Mock
    private FileContentRepository fileContentRepository;

    private MockMultipartFile mockFile;
    @BeforeEach
    public void setup() {
        mockFile = new MockMultipartFile("data", "filename.avi", "text/plain", "video".getBytes());
    }

    @Test
    public void GIVEN_upload_WHEN_video_THEN_save() throws IOException {

        fileService.setContent(mockFile, "actor");
        verify(fileContentRepository, atLeast(1)).save(any());
    }

    @Test
    public void GIVEN_upload_WHEN_videoPutMethod_THEN_save() throws IOException {
        when(fileContentRepository.findById(1L)).thenReturn(Optional.of(file));

        fileService.setUpdateContent(1L,mockFile);
        verify(fileContentRepository, atLeast(1)).save(any());
    }

    @Test
    public void GIVEN_getContent_WHEN_videoIsPlayed_THEN_video() throws IOException {
        when(fileContentRepository.findById(1L)).thenReturn(Optional.of(file));

        InputStream inputStream =
                IOUtils.toInputStream("test", "UTF-8");
        when(contentStore.getContent(file)).thenReturn(inputStream);
        assertNotNull(fileService.getContent(1L));
    }

    @Test
    public void GIVEN_video_WHEN_deleteVideo_THEN_delete() {
        when(fileContentRepository.findById(1L)).thenReturn(Optional.of(file));

        assertNotNull(fileService.deleteContent(1L));
    }



}
