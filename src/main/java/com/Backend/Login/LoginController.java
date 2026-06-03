package com.Backend.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Backend.Tables.UserDetails;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService ls;
	
	
	@PostMapping("/Login")
	public String login(@RequestBody UserDetails ud) {
	String s=ls.login(ud);
		return s;
	}

}
