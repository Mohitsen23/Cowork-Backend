package com.example.demo.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.Filter.AuthFilter;
import com.example.demo.ServiceImplementation.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private AuthFilter authFilter;

	 @Bean
	 public UserDetailsService userDetailsServices() {

		 return new UserInfoUserDetailsService();
		 
		 
	 }
//	 ,
	 @Bean
	 public SecurityFilterChain seucrityfilter(HttpSecurity http) throws Exception
	 {
		  return http.csrf().disable()
							.authorizeHttpRequests()
							.requestMatchers("/**").permitAll()
							.and()
							.authorizeHttpRequests()
							.requestMatchers("/download/{fileName}").authenticated()
							.and()
							.sessionManagement()
			                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			                .and()
			                .authenticationProvider(authenticationProvider())
			                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
							.build();
	 }
	 
	   @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	   
	 
	   @Bean
	   public PasswordEncoder encoder()
	   {
		   return new BCryptPasswordEncoder();
	   }
	   
	   
	   @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsServices());
	        authenticationProvider.setPasswordEncoder(encoder());
	        return authenticationProvider;
	    }
	   
	
}
