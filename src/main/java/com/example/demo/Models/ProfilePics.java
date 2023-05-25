package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	 @OneToOne
	 @JoinColumn(name="User_id")
	private Signup signup;
}
