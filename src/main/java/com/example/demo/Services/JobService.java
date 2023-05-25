package com.example.demo.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Models.Signup;

@org.springframework.stereotype.Service
public interface JobService {

	 
	String uploadImage(MultipartFile file, String name, String task, String description, Long price, String link,
			Signup user) throws IOException;






	List getJobsByAdmin(Signup id);



	
}
