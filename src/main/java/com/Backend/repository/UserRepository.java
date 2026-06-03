package com.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backend.Tables.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails,Integer>{

	Optional<UserDetails> findByEmail(String Email);

	
}
