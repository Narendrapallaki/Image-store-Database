package com.boardcastaMail.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class FileUploadForm {
    private MultipartFile file;
    private String subject;
}
