package com.image.userControllor;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.image.entity.Excel;
import com.image.entity.User;
import com.image.userService.UserService;


import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class UserControllor {

	
	@Autowired
	 private UserService userService;
	
	@GetMapping("/sam")
	public String sample(Model model)
	{
		
		model.addAttribute("excel", new Excel());
		return "sample";
	}
	
	
	
	@PostMapping("/ex")
	public String saveExcel(@ModelAttribute Excel excel ,@RequestParam("file") MultipartFile file)
	{
		
		System.out.println("Subject :"+excel.getSubject());
		System.out.println("file excel :"+excel.getFile().getOriginalFilename());
		System.out.println("multipart :"+file.getOriginalFilename());
		return "done";
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/wel")
	public String welcome(Model model)
	{
		model.addAttribute("user", new User());
		return "welcome";
	}
	


	 
	 @PostMapping("/save")
	    public String saveImage(@RequestParam("file") MultipartFile file, User user) 
	    		throws IOException, SerialException, SQLException {
	        userService.save(user, file);
	        return "success";
	    }
	 
	 
	 
}
