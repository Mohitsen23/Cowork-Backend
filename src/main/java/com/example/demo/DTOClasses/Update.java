package com.example.demo.DTOClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Update {
  private int id;
  private String email;
  private String password;
  
}
