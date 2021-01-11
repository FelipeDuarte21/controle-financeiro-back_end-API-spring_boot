package com.felipeduarte.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipeduarte.exceptions.ObjectBadRequestException;
import com.felipeduarte.models.Category;
import com.felipeduarte.services.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<Category> save(@RequestBody @Valid Category category){
		
		category = this.categoryService.save(category);
		
		if(category.getName() == null) throw new ObjectBadRequestException("Categoria já criada");
		
		return ResponseEntity.status(HttpStatus.OK).body(category);
		
	}
	
	@PutMapping
	public ResponseEntity<Category> update(@RequestBody @Valid Category category){
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		return null;
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Category>> findByName(
			@RequestParam String name, 
			@RequestParam Integer number, 
			@RequestParam Integer size){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<Page<Category>> findAll(
			@RequestParam Integer number, 
			@RequestParam Integer page){
		return null;
	}
	
}
