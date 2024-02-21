package com.boardcastaMail.controller;

import com.boardcastaMail.entity.FileUploadForm;
import com.boardcastaMail.service.BoardcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private BoardcastService service;


   
    @GetMapping("/get")
    public String getUpload()
    {
     //   model.addAttribute("FileUploadForm",new FileUploadForm());
        return "sample";
    }
    
    @PostMapping("/success-excel")
    public String get()
    {
     //   model.addAttribute("FileUploadForm",new FileUploadForm());
        return "success";
    }

    @PostMapping("/upload-excel")
    public String uploadFile(Model model, @RequestParam  MultipartFile file,FileUploadForm fileUploadForm)
    {
        String message = "";

        try {
           this. service.uploadExcel(file,fileUploadForm.getSubject());

            message = "Uploaded the file successfully ";
            model.addAttribute("fileUploadForm", message);
        } catch (Exception e) {
            message = "Could not upload the file:  Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        return "success";
    }


}
