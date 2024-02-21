package com.boardcastaMail.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface BoardcastService {
    Map<String, Object> uploadExcel(MultipartFile file,String subject) throws Exception;
}
