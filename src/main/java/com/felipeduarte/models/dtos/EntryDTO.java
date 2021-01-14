package com.felipeduarte.models.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EntryDTO {
	
	private Long id;
	
	@NotBlank(message = "nome deve ser informado!")
	@Size(min = 3, max = 50, message = "Nome deve ter entre 3 a 50 caracteres")
	private String name;
	
	@NotNull(message = "valor deve ser informado")
	private Double value;
	
	@NotBlank
	@Size(max = 150, message = "Descrição deve ter no máximo 150 caracteres")
	private String description;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "data deve ser informada!")
	private Date date;
	
	@NotNull(message = "tipo de entrada deve ser informado")
	private Integer entryType;
	
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

	public Integer getEntryType() {
		return entryType;
	}

	public void setEntryType(Integer entryType) {
		this.entryType = entryType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
