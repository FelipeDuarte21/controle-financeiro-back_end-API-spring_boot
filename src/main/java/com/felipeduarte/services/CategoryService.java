package com.felipeduarte.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.Category;
import com.felipeduarte.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		
		Optional<Category> optCategory = this.categoryRepository.findByName(category.getName());
		
		if(optCategory.isPresent()) {
			category.setName(null);
			return category;
		}
		
		category = this.categoryRepository.save(category);
		
		return category;
	}
	
	public Category update(Category category) {
		return null;
	}
	
	public boolean delete(Long id) {
		return false;
	}
	
	public Category findById(Long id) {
		return null;
	}
	
	public Page<Category> findByName(String name, Integer number, Integer size){
		return null;
	}
	
	public Page<Category> findAll(Integer number, Integer size){
		return null;
	}
	
	
}
