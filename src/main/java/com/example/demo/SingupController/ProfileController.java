package com.example.demo.SingupController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Models.Signup;
import com.example.demo.SecurityConfig.RepositoryClass;
import com.example.demo.Services.ProfileService;

@RestController
public class ProfileController {
	@Autowired
	private RepositoryClass repo;
	@Autowired
  private ProfileService ProfileService;
	@PostMapping("/profile")
	@CrossOrigin(origins = "/**")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("email")String email) throws IOException {
		System.out.println("hitting");
		Signup user=repo.findByEmail(email);
		
		if(user!=null) {
			
		String uploadImage = ProfileService.uploadImage(file,user);
	
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
		}
		return null;
	}
	
//	 @GetMapping("/download/{fileName}")
//		public ResponseEntity<?> downloadImage(@PathVariable String fileName){
//			byte[] imageData=ProfileService.downloadImage(fileName);
//			return ResponseEntity.status(HttpStatus.OK)
//					.contentType(MediaType.valueOf("image/png"))
//					.body(imageData);
//			}
//	

}
