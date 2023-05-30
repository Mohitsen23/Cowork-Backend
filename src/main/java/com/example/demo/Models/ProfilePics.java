package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Profile")
public class ProfilePics {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	 @Lob
	 private String name;
	 private byte[] file;
	@OneToOne()
	@JsonManagedReference
	private Signup signup;
}
