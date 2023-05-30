package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Signup {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstname;
	
	private String lastname;

	private String email;
	
	private String password;
	
	private String role;

    @OneToOne
    @JoinColumn(name="userprofile")
	@JsonBackReference
	private ProfilePics userProfile;

}
