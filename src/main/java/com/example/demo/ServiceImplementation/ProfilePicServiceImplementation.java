package com.example.demo.ServiceImplementation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ImageUtils.ImageUtils;
import com.example.demo.Models.ProfilePics;
import com.example.demo.Models.Signup;
import com.example.demo.Repository.ProfileRepo;
import com.example.demo.Services.ProfileService;

@Service
public class ProfilePicServiceImplementation implements ProfileService {
    @Autowired
    private ProfileRepo repository;
	@Override
	public String uploadImage(MultipartFile file, Signup user) throws IOException {
		   ProfilePics imageData = repository.save(ProfilePics.builder()
                     .signup(user)
                   .file(ImageUtils.compressImage(file.getBytes())).build());
if (imageData != null) {
return "file uploaded successfully : " ;
}
return null;
	}
	@Override
	public byte[] downloadImage(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
