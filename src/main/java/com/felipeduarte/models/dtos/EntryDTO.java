package com.felipeduarte.models.dtos;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EntryDTO {
	
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 50, message = "Nome deve ter entre 3 a 50 caracteres")
	private String name;
	
	@Min(value = 0)
	private Double value;
	
	@NotBlank
	@Size(max = 150, message = "Descrição deve ter no máximo 150 caracteres")
	private String description;
	
	private Date date;
	
	private Long categoryId;
	
	public EntryDTO() {
		
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
