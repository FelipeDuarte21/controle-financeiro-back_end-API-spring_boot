package com.felipeduarte.models.dtos;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTOWithPassword {
	
	private Long id;
	
	@NotBlank(message = "nome é requerido")
	@Size(min = 3,max = 50, message = "Nome ente 3 a 50 caracteres")
	private String name;
	
	@NotBlank(message = "email é requerido")
	@Email(message = "Email inválido")
	@Size(max = 80, message = "Email com até 80 caracteres")
	private String email;
	
	@NotNull(message = "senha é requerida")
	@Size(min=8, max=16, message = "senha deve ter entre 8 a 16 caracteres")
	private String password;
	
	@NotNull(message = "tipo do usuario é requerido")
	private Set<Integer> types;
	
	public UserDTOWithPassword() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Integer> getTypes() {
		return types;
	}

	public void setTypes(Set<Integer> types) {
		this.types = types;
	}
	
}
