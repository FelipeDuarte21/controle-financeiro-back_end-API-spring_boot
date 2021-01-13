package com.felipeduarte.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.User;
import com.felipeduarte.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Por enquanto
	@Autowired
	private UserService userService;
	
	public Category save(Category category) {
		
		//Por enquanto
		User user = this.userService.findById(1L);
		
		Optional<Category> optCategory = this.categoryRepository.findByNameAndUser(category.getName(),user);
		
		if(optCategory.isPresent()) {
			category.setName(null);
			return category;
		}
		
		category.setUser(user);
		
		category = this.categoryRepository.save(category);
		
		return category;
	}
	
	public Category update(Category category) {
		
		if(category.getId() == null) return category;
		
		Category c = this.findById(category.getId());
		
		if(c == null) {
			category.setName(null);
			return category;
		}
		
		category.setUser(c.getUser());
		
		category = this.categoryRepository.save(category);
		
		return category;
	}
	
	public boolean delete(Long id) {
		
		Category category = this.findById(id);
		
		if(category == null) return false;
		
		this.categoryRepository.delete(category);
		
		return true;
	}
	
	public Category findById(Long id) {
		
		Optional<Category> categoryOptional = this.categoryRepository.findById(id);
		
		if(categoryOptional.isEmpty()) return null;
		
		return categoryOptional.get();
	}
	
	public Page<Category> findByName(String name, Integer number, Integer size){
		
		PageRequest page = PageRequest.of(number, size,Direction.ASC, "name");
		
		//Por enquanto
		User user = this.userService.findById(1L);
		
		Page<Category> pageCategory;
		
		if(name.isEmpty()) {
			pageCategory = this.findAll(number, size);
		}else {
			pageCategory = this.categoryRepository.findByUserAndNameContaining(user, name, page);
		}
		
		return pageCategory;
	}
	
	public Page<Category> findAll(Integer number, Integer size){
		
		PageRequest page = PageRequest.of(number, size, Direction.ASC, "name");
		
		//Por enquanto
		User user = this.userService.findById(1L);
		
		Page<Category> pageCategory = this.categoryRepository.findByUser(user, page);
		
		return pageCategory;
	}
	
	
}
