package com.felipeduarte.resources;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.felipeduarte.models.Entry;
import com.felipeduarte.models.dtos.EntryDTO;
import com.felipeduarte.services.EntryService;

@RestController
@RequestMapping("/entry")
public class EntryResource {
	
	@Autowired
	private EntryService entryService;
	
	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping
	public ResponseEntity<Entry> save(@RequestBody @Valid EntryDTO entryDTO){
		
		Entry entry = this.entryService.save(entryDTO);
		
		if(entry.getCategory() == null) throw new ObjectBadRequestException("id da categoria inválido!");
		
		return ResponseEntity.status(HttpStatus.OK).body(entry);
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping
	public ResponseEntity<Entry> update(@RequestBody @Valid EntryDTO entryDTO){
		
		Entry entry = this.entryService.update(entryDTO);
		
		if(entry.getId() == null) throw new ObjectBadRequestException("Informe o id da entrada!");
		
		if(entry.getName() == null) throw new ObjectBadRequestException("entrada não encontrada, verifique o id informado!");
		
		return ResponseEntity.status(HttpStatus.OK).body(entry);
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		boolean resp = this.entryService.delete(id);
		
		if(resp == false) throw new ObjectNotFoundException("Entrada não encontrada, verifique o id!");
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Entry> findById(@PathVariable Long id){
		
		Entry entry = this.entryService.findById(id);
		
		if(entry == null) {
			throw new ObjectNotFoundException("Nada encontrado para o id Informado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(entry);
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("category/{categoryId}")
	public ResponseEntity<Page<Entry>> findAll(
			@PathVariable Long categoryId,
			@RequestParam(defaultValue = "0") Integer number, 
			@RequestParam(defaultValue = "3") Integer size){
		
		Page<Entry> pageEntry = this.entryService.findAll(categoryId, number, size);
		
		if(pageEntry == null) throw new ObjectNotFoundException("categoria não encontrada, verifique o id!");
		
		return ResponseEntity.status(HttpStatus.OK).body(pageEntry);	
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("category/{categoryId}/search")
	public ResponseEntity<Page<Entry>> findByNameContaining(
			@PathVariable Long categoryId,
			@RequestParam String name, 
			@RequestParam(defaultValue = "0") Integer number, 
			@RequestParam(defaultValue = "3") Integer size){
		
		Page<Entry> pageEntry = this.entryService.findByNameContaining(categoryId, name, number, size);
		
		if(pageEntry == null) throw new ObjectNotFoundException("categoria não encontrada, verifique o id!");
		
		return ResponseEntity.status(HttpStatus.OK).body(pageEntry);
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("category/{categoryId}/search/date")
	public ResponseEntity<Page<Entry>> findByDateBetween(
			@PathVariable Long categoryId,
			@RequestParam Date start, 
			@RequestParam Date end, 
			@RequestParam(defaultValue = "0") Integer number, 
			@RequestParam(defaultValue = "3") Integer size){
		
		Page<Entry> pageEntry = this.entryService.findByDateBetween(categoryId, start, end, number, size);
		
		if(pageEntry == null) throw new ObjectNotFoundException("categoria não encontrada, verifique o id!");
		
		return ResponseEntity.status(HttpStatus.OK).body(pageEntry);
		
	}
	
}
