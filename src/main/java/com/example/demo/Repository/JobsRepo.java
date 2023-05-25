package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Jobs;
import com.example.demo.Models.Signup;

@Repository
public interface JobsRepo extends JpaRepository<Jobs,Long>{

	

	List findBysignup(Signup id);

}
