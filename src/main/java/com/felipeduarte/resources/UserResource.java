package com.felipeduarte.resources;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
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

import com.felipeduarte.models.User;
import com.felipeduarte.models.dtos.UserDTO;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid User user){
		return null;
	}
	
	@PutMapping
	public ResponseEntity<User> update(@RequestBody @Valid UserDTO user){
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return null;
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<User>> findByName(@RequestParam String name, 
			@RequestParam Integer number, 
			@RequestParam Integer size){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> findAll(
			@RequestParam Integer number, 
			@RequestParam Integer size){
		return null;
	}
	
}
