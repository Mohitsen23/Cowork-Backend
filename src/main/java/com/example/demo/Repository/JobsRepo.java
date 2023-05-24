package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Jobs;

@Repository
public interface JobsRepo extends JpaRepository<Jobs,Long>{

}
