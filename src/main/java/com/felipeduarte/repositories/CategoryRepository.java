package com.felipeduarte.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	public Page<Category> findByUser(User user, Pageable page);
	
	public Optional<Category> findByName(String name);
	
	public Page<Category> findByNameContaining(String name, Pageable page);
	
}
