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
import com.felipeduarte.exceptions.ObjectNotFoundException;
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
		
		category = this.categoryService.update(category);
		
		if(category.getId() == null) throw new ObjectBadRequestException("Informe um id!");
		
		if(category.getName() == null) throw new ObjectBadRequestException("Id inválido!, verifique o id");
		
		return ResponseEntity.status(HttpStatus.OK).body(category);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		boolean resp = this.categoryService.delete(id);
		
		if(resp == false) throw new ObjectNotFoundException("Categoria não encontrada!, "
				+ "verifique o id informado!");
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		
		Category category = this.categoryService.findById(id);
		
		if(category == null) throw new ObjectNotFoundException("Categoria não encontrada!");
		
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Category>> findByName(
			@RequestParam(defaultValue = "") String name, 
			@RequestParam(defaultValue = "0") Integer number, 
			@RequestParam(defaultValue = "3") Integer size){
		
		Page<Category> pageCategory = this.categoryService.findByName(name, number, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(pageCategory);
	}
	
	@GetMapping
	public ResponseEntity<Page<Category>> findAll(
			@RequestParam Integer number, 
			@RequestParam Integer page){
		return null;
	}
	
}
