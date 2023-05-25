package com.example.demo.ServiceImplementation;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.EmailUtil.EmailConfigure;
import com.example.demo.Models.Signup;
import com.example.demo.SecurityConfig.RepositoryClass;
import com.example.demo.Services.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@org.springframework.stereotype.Service
public class ServiceImplementation implements Service{
   @Autowired
   private RepositoryClass repo;
   @Autowired
   private EmailConfigure user;
   

   
   public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
   }

   public Date extractExpiration(String token) {
       return extractClaim(token, Claims::getExpiration);
   }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
       final Claims claims = extractAllClaims(token);
       return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
       return Jwts
               .parserBuilder()
               .setSigningKey(getKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
   }

   private Boolean isTokenExpired(String token) {
       return extractExpiration(token).before(new Date());
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
       final String username = extractUsername(token);
       return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }
   
   
   
   
public String getTOken(String username) {
	   Map<String ,Object> claims= new HashMap<>();
	   return  CreateToken(username,claims);
   }
  
   private String CreateToken(String username, Map<String, Object> claims) {
	
	return Jwts.builder()
			.setClaims(claims)
			.setSubject(username)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+ 600000))
			.signWith(getKey(),SignatureAlgorithm.HS256).compact();
			
       }

private Key getKey() {
final String key="AVQ4XgHTIPKVV2h27nCBkTGb7NK3QEghlB1sYYoNlXsEzKTv8YAXWdBp6cH4yc";
	byte[] keyBytes=Decoders.BASE64.decode(key);
	return Keys.hmacShaKeyFor(keyBytes);
	
}



@Override
public Optional<Signup> getLogin(String username) {
	
Optional<Signup> sign=repo.findUserByEmail(username);
	System.out.println("signin Implementation"+sign);
	if(sign!=null) {
		return sign;
	}
	
	return null;
}

@Override
public Signup getUser(String email) {
	// TODO Auto-generated method stub
	    Signup user=repo.findByEmail(email);
	    if(user!=null) {
	    	return user;
	    }
	return null;
}





}
