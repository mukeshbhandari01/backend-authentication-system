package com.Backend.JWT_Token;
	import java.security.Key;
	import java.util.Base64;
	import java.util.HashMap;
import java.util.List;
import java.util.Map;
    import java.util.Optional;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    import com.Backend.Tables.UserDetails;
    import com.Backend.repository.UserRepository;
    import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.io.Decoders;
    import io.jsonwebtoken.security.Keys;

    @Component
	public class JWT_Token_Generation {

		@Autowired
		private UserRepository repo;
		
		// key generation
		private String secretkey = Base64.getEncoder()
		        .encodeToString("my-super-secret-key-which-is-very-strong-Because-we-use-cryptography-algorithms-123456".getBytes());
		
		private Key getkey() {
			byte[] keyBytes=Decoders.BASE64.decode(secretkey);
			return Keys.hmacShaKeyFor(keyBytes);
		}

		
		// token generation
		public String generateToken(String username) {
			Map<String,Object> claims=new HashMap<>();
			
			Optional<UserDetails> t= repo.findByEmail(username);
		     UserDetails g=t.get();
			return Jwts.builder()
		            .setClaims(claims)
		            .setSubject(username)
		            .claim("roles", List.of(g.getRole()))
		            .setIssuedAt(new Date(System.currentTimeMillis()))
		            .setExpiration(
		                    new Date(System.currentTimeMillis() + 1000 * 60 * 60)
		            )
		            .signWith(getkey())
		            .compact();	
		}

		// validatiom
		
		public Claims extractClaims(String token) {
			
			
			return Jwts.parserBuilder()
					.setSigningKey(getkey())
					.build()
					.parseClaimsJws(token)
					.getBody();
					}

		public String extractusername(String token) {
			return extractClaims(token).getSubject();
		}

		public boolean validateToken(String token, String username) {
			try {
				UserDetails k=repo.findByEmail(username).orElseThrow();
				return username.equals(k.getEmail());
			}catch(Exception e){
				return false;
			}
		}


		public List<String> extractrole(String token) {
					List<String> l= (List<String>) extractClaims(token).get("roles",List.class);
					return l;
		}

    }
