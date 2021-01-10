package com.felipeduarte.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryService save(CategoryService category) {
		return null;
	}
	
	public CategoryService update(CategoryService category) {
		return null;
	}
	
	public boolean delete(Long id) {
		return false;
	}
	
	public CategoryService findById(Long id) {
		return null;
	}
	
	public Page<CategoryService> findByName(String name, Integer number, Integer size){
		return null;
	}
	
	public Page<CategoryService> findAll(Integer number, Integer size){
		return null;
	}
	
	
}
