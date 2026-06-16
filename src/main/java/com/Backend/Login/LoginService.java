package com.Backend.Login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.Backend.repository.*;
import org.springframework.stereotype.Service;

import com.Backend.Ecxeptions.UserNotFoundException;
import com.Backend.Ecxeptions.WrongPasswordException;
import com.Backend.JWT_Token.JWT_Token_Generation;
import com.Backend.Tables.UserDetails;

@Service
public class LoginService {
	
	@Autowired
	private JWT_Token_Generation jwttoken;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder pe;
	
	public String login(UserDetails ud) {
		Optional<UserDetails> t= repo.findByEmail(ud.getEmail());
		
	    if (t.isEmpty()) {
	        throw new UserNotFoundException("User Not Found");
	    }else {
		     UserDetails g=t.get();
		     boolean isValid=pe.matches(ud.getPassword(), g.getPassword());
		   
		         if(isValid) {
		        	 String token=jwttoken.generateToken(g.getEmail());
			          return token;
		         }else {
		        	throw new WrongPasswordException("Wrong Password");
		         }

	}
}
}
	

