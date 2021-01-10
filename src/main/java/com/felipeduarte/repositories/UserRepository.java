package com.felipeduarte.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeduarte.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	public Optional<User> findByEmail(String email);
	
	public Page<User> findByName(String name, Pageable page);
	
}