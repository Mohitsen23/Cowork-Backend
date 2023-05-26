package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.ProfilePics;

@Repository
public interface ProfileRepo extends JpaRepository<ProfilePics, Long>{

	Optional<ProfilePics> findByName(String fileName);


	@Query("SELECT u FROM ProfilePics u WHERE u.signup.id = :userId")
	ProfilePics findByUserId(@Param("userId") Long userId);







}
