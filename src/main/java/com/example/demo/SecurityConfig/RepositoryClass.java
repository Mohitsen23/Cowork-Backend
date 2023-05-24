package com.example.demo.SecurityConfig;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Signup;


@Repository
public interface RepositoryClass extends JpaRepository<Signup,Long>{
   //@Query( value = "select * from Signup u where u.email = email" , nativeQuery = true)
	Optional<Signup> findUserByEmail(String email);
	
	//@Query( value = "select * from Signup u where u.email = email" , nativeQuery = true)
		Signup findByEmail(String email);
		
}
