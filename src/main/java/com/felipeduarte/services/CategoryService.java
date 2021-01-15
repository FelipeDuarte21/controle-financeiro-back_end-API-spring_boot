package com.felipeduarte.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipeduarte.exceptions.AuthorizationException;
import com.felipeduarte.models.Category;
import com.felipeduarte.models.User;
import com.felipeduarte.repositories.CategoryRepository;
import com.felipeduarte.security.UserDetailsAdapter;
import com.felipeduarte.security.UserDetailsServiceAdapter;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		
		User user = CategoryServicePermission.getUserLogged();
		
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
		
		CategoryServicePermission.verifyUserId(category.getUser().getId());
		
		category = this.categoryRepository.save(category);
		
		return category;
	}
	
	public boolean delete(Long id) {
		
		Category category = this.findById(id);
		
		if(category == null) return false;
		
		CategoryServicePermission.verifyUserId(category.getUser().getId());
		
		this.categoryRepository.delete(category);
		
		return true;
	}
	
	public Category findById(Long id) {
		
		Optional<Category> categoryOptional = this.categoryRepository.findById(id);
		
		if(categoryOptional.isEmpty()) return null;
		
		CategoryServicePermission.verifyUserId(categoryOptional.get().getUser().getId());
		
		return categoryOptional.get();
	}
	
	public Page<Category> findByName(String name, Integer number, Integer size){
		
		PageRequest page = PageRequest.of(number, size,Direction.ASC, "name");
		
		User user = CategoryServicePermission.getUserLogged();
		
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
		
		User user = CategoryServicePermission.getUserLogged();
		
		Page<Category> pageCategory = this.categoryRepository.findByUser(user, page);
		
		return pageCategory;
	}
	
	private static class CategoryServicePermission{
		
		public static User getUserLogged() {
			
			UserDetailsAdapter user = (UserDetailsAdapter) UserDetailsServiceAdapter.getUser();
			
			if(user != null) {
				
				User u = new User();
				u.setId(user.getId());
				u.setName(user.getName());
				u.setEmail(user.getUsername());
				u.setPassword(user.getPassword());
				
				return u;
				
			}else {
				throw new AuthorizationException("Usuário não logado!");
			}
			
		}
		
		public static void verifyUserId(Long comparedId) {
			User user = getUserLogged();
			
			if(!user.getId().equals(comparedId)) {
				throw new AuthorizationException("Esta categoria não pertence ao usuário logado!");
			}
			
		}
		
	}
}
