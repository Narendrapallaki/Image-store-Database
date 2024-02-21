package com.image.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Excel {
	
	
	public MultipartFile file;
	public String subject;

}
