package com.example.demo.Services;

import java.util.Optional;

import com.example.demo.DTOClasses.Email;
import com.example.demo.DTOClasses.SignupDTO;
import com.example.demo.Models.Signup;

@org.springframework.stereotype.Service
public interface Service {

	Optional<Signup> getLogin(String username);

}
