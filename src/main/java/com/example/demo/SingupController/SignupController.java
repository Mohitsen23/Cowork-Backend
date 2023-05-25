package com.example.demo.SingupController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOClasses.OTP;
import com.example.demo.DTOClasses.SignupDTO;
import com.example.demo.EmailUtil.EmailConfigure;
import com.example.demo.Models.Signup;
import com.example.demo.SecurityConfig.RepositoryClass;
import com.example.demo.Services.Service;

@RestController
@CrossOrigin
public class SignupController {
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private EmailConfigure email;
	
	 @Autowired
	   private RepositoryClass repo;
	
	String value;
	 Signup obj=new Signup();

	 boolean status =false;
     @PostMapping("/signup")
     @CrossOrigin(origins = "http://localhost:4200")
	public String signupDetails(@RequestBody SignupDTO sign){
    	 value=null;
    	 Signup data= repo.findByEmail(sign.getEmail());
    	 System.out.println("data"+data);
    	 
    	 if(data==null) {  
    		 System.out.println("data"+data);
    	 value=email.sendEmailWithAttachMent(sign.getEmail());
    	obj.setFirstname(sign.getFirstname());
 		obj.setLastname(sign.getLastname());
 		obj.setEmail(sign.getEmail());
 		obj.setPassword(encoder.encode(sign.getPassword()));
 		obj.setRole(sign.getRole());
    	 }
 		
    	    
    	 if(value!=null) {
    		 System.out.println(value);
    		return "OTP sent successfully";
    	 }
    	 else {
    		 return null;
    	 }
		
	}
     
     @GetMapping("/verify/{otp}")
     public String getVerify(@PathVariable("otp") String otp) {
		System.out.println("otp"+otp);
		System.out.println("email.otp"+email.otp);
		    try {
			if(otp.equals(email.otp)) {
				 repo.save(obj);
				 return "added user";
			 }
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
     }
}
