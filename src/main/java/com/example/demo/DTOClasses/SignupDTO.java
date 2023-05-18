package com.example.demo.DTOClasses;

import com.example.demo.Models.Signup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
   private int id;
	
	private String firstname;
	private String lastname;

	private String email;

	private String password;
	private String role;
}
	

