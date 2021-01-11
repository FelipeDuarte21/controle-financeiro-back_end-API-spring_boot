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
import com.felipeduarte.models.User;
import com.felipeduarte.models.dtos.UserDTO;
import com.felipeduarte.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid User user){
		
		user = this.userService.save(user);
		
		if(user == null) throw new ObjectBadRequestException("Usuário já cadastrado!");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PutMapping
	public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO){
		
		userDTO = this.userService.update(userDTO);
		
		if(userDTO.getId() == null) throw new ObjectBadRequestException("Id não pode ser nullo");
		
		if(userDTO.getName() == null) throw new ObjectBadRequestException("Usuario não encontrado!");
		
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		boolean resp = this.userService.delete(id);
		
		if(resp == false) throw new ObjectNotFoundException("Usuário não encontrado!");
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<User>> findByName(@RequestParam String name, 
			@RequestParam(defaultValue = "0") Integer number, 
			@RequestParam(defaultValue = "3") Integer size){
		
		Page<User> pageUser = this.userService.findByNome(name, number, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(pageUser);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> findAll(
			@RequestParam Integer number, 
			@RequestParam Integer size){
		return null;
	}
	
}
