package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.ProfilePics;

@Repository
public interface ProfileRepo extends JpaRepository<ProfilePics, Long>{

}
