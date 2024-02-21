package com.image.userService;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.entity.User;
import com.image.userRepository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void save(User user,MultipartFile multipartFile) throws SerialException, SQLException, IOException
	{
		log.info(multipartFile.getOriginalFilename());
		  // User user=new User();
		   
		   user.setImage(new javax.sql.rowset.serial.SerialBlob(multipartFile.getBytes()));
		   
		   
		   User save = userRepository.save(user);
		   log.info("save user id : ",String.valueOf(save.getId()));
	}

}
