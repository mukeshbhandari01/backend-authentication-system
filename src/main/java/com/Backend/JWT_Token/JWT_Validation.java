package com.Backend.JWT_Token;



import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.Backend.repository.UserRepository;

import java.util.List;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWT_Validation extends OncePerRequestFilter{
	
	@Autowired
	private JWT_Token_Generation jt;
	
	@Autowired
	private UserRepository repo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String Token=null;
		String username=null;


		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			Token =authHeader.substring(7);

			username= jt.extractusername(Token);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			boolean validtoken=jt.validateToken(Token,username);
			if(validtoken==true) {
				
				List<String> roles = jt.extractrole(Token);

				List<SimpleGrantedAuthority> authorities =
				        roles.stream()
				             .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
				             .toList();

				UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(username,null,authorities);
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
		}
      filterChain.doFilter(request,response);
		
	}
}
