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

//							it will info about profile pic is their or not
		System.out.println("user getting"+user);
                           var profilePic = repository.findByUserId(user.getId());
//						   it will give the use details
						   Signup signup=repo.findByEmail(user.getEmail());

						   if(profilePic==null) {
							   var returnProfile =repository.save(ProfilePics.builder()
									   .signup(user)
									   .name(file.getOriginalFilename())
									   .file(ImageUtils.compressImage(file.getBytes())).build());
							  Signup signup1 = signup;
							  signup1.setUserProfile(returnProfile);
							   repo.save(signup1);
								   System.out.println("gettting test :: " + returnProfile);
								   return returnProfile;

						   }else {
							   ProfilePics profilePics= new ProfilePics();
							   profilePics.setId(profilePic.getId());
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
