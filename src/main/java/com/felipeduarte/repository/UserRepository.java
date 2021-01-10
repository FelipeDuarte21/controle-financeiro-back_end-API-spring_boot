package com.felipeduarte.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeduarte.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	public Page<User> findByName(String name, Pageable page);
	
}