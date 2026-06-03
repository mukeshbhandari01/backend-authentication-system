package com.Backend.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Backend.Tables.UserDetails;

@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService Rs;

	@PostMapping("/Register")
	public String register(@RequestBody UserDetails u) {

		String s=Rs.InsertAtDataBase(u);
		return s;
		
	}
	
	
	@GetMapping("/user")
	public String hello() {
		return "you are user";
		
	}

	@GetMapping("/admin")
	public String ad() {
		return "you are admin";
	}
}
