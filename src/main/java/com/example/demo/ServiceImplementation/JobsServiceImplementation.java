package com.example.demo.ServiceImplementation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ImageUtils.ImageUtils;
import com.example.demo.Models.Jobs;
import com.example.demo.Models.Signup;
import com.example.demo.Repository.JobsRepo;
import com.example.demo.Services.JobService;

@Service
public class JobsServiceImplementation implements JobService{
	@Autowired
	private JobsRepo repo;


	@Override
	public String uploadImage(MultipartFile file, String name, String task, String description, Long price, String link,
		Signup user) throws IOException {
		Jobs imageData = repo.save(Jobs.builder()
                .title(name) 
                .task(task)
                .description(description)
                .price(price)
                .link(link)
                .signup(user)
                .file(ImageUtils.compressImage(file.getBytes())).build());
		
if (imageData != null) {
return "file uploaded successfully : " ;
}
return null;
	}


	@Override
	public List getJobsByAdmin(Signup id) {
		// TODO Auto-generated method stub
		
		List jobs=repo.findBysignup(id);
		if(jobs!=null) {
			return jobs;
		}
		return null;
	}

}
