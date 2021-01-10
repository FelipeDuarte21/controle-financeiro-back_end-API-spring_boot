package com.felipeduarte.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	public Page<Category> findByUser(User user, Pageable page);
	
	public Page<Category> findByName(String name, Pageable page);
	
}