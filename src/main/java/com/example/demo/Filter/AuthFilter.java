package com.example.demo.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.ServiceImplementation.ServiceImplementation;
import com.example.demo.ServiceImplementation.UserInfoUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter{
	@Autowired
	private ServiceImplementation serviceimplementation;
	 @Autowired
	    private UserInfoUserDetailsService userDetailsService;

	
	@SuppressWarnings("unused")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	   String token =null;
	String username = null;
	   String authHeader=request.getHeader("Authorization");
	   
	   if(authHeader!=null && authHeader.startsWith("Bearer")) {
		     token=authHeader.substring(7);
		     username = serviceimplementation.extractUsername(token);
		     System.out.println("token"+token);
	   }
	   
	   if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = userDetailsService.loadUserByUsername(username);
           if (serviceimplementation.validateToken(token, userDetails)) {
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }
       }
	   
       filterChain.doFilter(request, response);
			


}
}

