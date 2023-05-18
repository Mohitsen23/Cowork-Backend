package com.example.demo.SingupController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOClasses.LoginDTO;
import com.example.demo.Models.Signup;
import com.example.demo.ServiceImplementation.ServiceImplementation;
import com.example.demo.Services.Service;

@RestController
@CrossOrigin
public class LoginController {
	
@Autowired
private AuthenticationManager authenticationManager;
	
    @Autowired
	private ServiceImplementation service;
    
    @Autowired
    private Service serv;
    String token=null;
    

    @PostMapping("/loginUser")
   public String login(@RequestBody LoginDTO data) {
    	System.out.println("loginDto data"+ data.getUsername());
    	Signup email=serv.getLogin(data.getUsername()).get();
	     
     System.out.println("email"+email);
	    Authentication authenticated =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())); 

	     if(email!=null) {
          if(authenticated.isAuthenticated()) {
	        
	    	 token= service.getTOken(email.getEmail());
	    	System.out.println("token"+token);
	    	 return token;
	    	 }
	     }
	     
		return null;
	   
   }
   
    
   @GetMapping("/admin")
   @PreAuthorize("hasAuthority('Role_Admin')")
   public String getData1() {
	   return "Admin Data";
   }
   
   @GetMapping("/user")
  @PreAuthorize("hasAuthority('Role_User')")
   public String getData2() {
	   return "User Data";
   }
   
   
   
   
   
   
    }
