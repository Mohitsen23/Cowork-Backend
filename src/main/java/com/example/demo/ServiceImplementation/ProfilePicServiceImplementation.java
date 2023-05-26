package com.example.demo.ServiceImplementation;

import java.io.IOException;
import java.util.Optional;

import com.example.demo.SecurityConfig.RepositoryClass;
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
	@Autowired
	private RepositoryClass repo;
	@Override
	public  ProfilePics uploadImage(MultipartFile file, Signup user) throws IOException {

                           var test = repository.findByUserId(user.getId());
						   Signup signup=repo.findByEmail(user.getEmail());

						   if(test==null) {
							  signup.setUserProfile(test);
								   System.out.println("gettting test :: " + test);
								   return repository.save(ProfilePics.builder()
										   .signup(user)
										   .name(file.getOriginalFilename())
										   .file(ImageUtils.compressImage(file.getBytes())).build());

						   }else {
							   ProfilePics profilePics= new ProfilePics();
							   profilePics.setId(test.getId());
							   profilePics.setFile(ImageUtils.compressImage(file.getBytes()));
							   profilePics.setName(file.getOriginalFilename());
							   profilePics.setSignup(user);
							   return repository.save(profilePics);

						   }
	}
	@Override
	public byte[] downloadImage(String fileName) {
	    Optional<ProfilePics> dbImageData = repository.findByName(fileName);
	    System.out.println("dbImageData"+dbImageData);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getFile());
        return images;
	}

}
