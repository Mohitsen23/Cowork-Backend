package com.example.demo.SingupController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Models.Signup;
import com.example.demo.SecurityConfig.RepositoryClass;
import com.example.demo.Services.JobService;

@RestController
public class JobsController {


	@Autowired
	private RepositoryClass repo;
	@Autowired
	private JobService jobservice;

	@PostMapping("/addJobs")
	@CrossOrigin(origins = "/**")
	
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("title")String title,@RequestParam("task")String task,@RequestParam("description")String description,@RequestParam("price")Long price,@RequestParam("link")String link,@RequestParam("user")String id ) throws IOException {
	    System.out.println("hitting");
		Signup user=repo.findByEmail(id);
	    	if(user!=null) {
		String uploadImage = jobservice.uploadImage(file,title,task,description,price,link,user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	    	}
	    	return null;
	}
	
	

}
