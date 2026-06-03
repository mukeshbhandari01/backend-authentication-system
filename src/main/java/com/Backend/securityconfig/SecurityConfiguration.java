package com.Backend.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Backend.JWT_Token.JWT_Validation;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JWT_Validation jwtfilter;

	@Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http
	         .csrf(csrf -> csrf.disable()) // csrf disable
	         .formLogin(form -> form.disable())   // Default login page disable
	         .httpBasic(httpBasic -> httpBasic.disable()) // Basic Auth disable
	         .authorizeHttpRequests(auth -> auth
	                 .requestMatchers("/Register",
	                		    "/swagger-ui/**",
	                		    "/v3/api-docs/**",
	                		    "/swagger-ui.html",
	                		    "/Login"
	                		).permitAll()
	                 .requestMatchers("/user").hasRole("USER")
	                 .requestMatchers("/admin").hasRole("ADMIN")
	                 .anyRequest().authenticated()
	         )
	         .addFilterBefore(jwtfilter,UsernamePasswordAuthenticationFilter.class);
		 return http.build();
}
}
