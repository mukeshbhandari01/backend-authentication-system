package com.Backend.Register;
import com.Backend.repository.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Backend.Ecxeptions.UserAlreadyExistException;
import com.Backend.Tables.UserDetails;


@Service
public class RegisterService {
	
	@Autowired
    private UserRepository repo;
	
	@Autowired
	private PasswordEncoder pe;
	
	public String InsertAtDataBase(UserDetails r) {

	     Optional<UserDetails> o=repo.findByEmail(r.getEmail());
	     if(o.isPresent()) {
	    	 throw new UserAlreadyExistException("Email already Registered");
	     }
	     
        r.setPassword(pe.encode(r.getPassword()));
		repo.save(r);
		return "User Register SuccessFully";
	}
}
