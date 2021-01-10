package com.felipeduarte.model.dtos;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
	
	private String id;
	
	@NotBlank
	@Size(min = 3,max = 50, message = "Nome ente 3 a 50 caracteres")
	private String name;
	
	@NotBlank
	@Email(message = "Email inválido")
	@Size(max = 80, message = "Email com até 80 caracteres")
	private String email;
	
	private Set<Integer> types;
	
	public UserDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Integer> getTypes() {
		return types;
	}

	public void setTypes(Set<Integer> types) {
		this.types = types;
	}
	
}
