package com.felipeduarte.resources;

import java.util.Date;

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
import com.felipeduarte.models.Entry;
import com.felipeduarte.models.dtos.EntryDTO;
import com.felipeduarte.services.EntryService;

@CrossOrigin
@RestController
@RequestMapping("/entry")
public class EntryResource {
	
	@Autowired
	private EntryService entryService;
	
	@PostMapping
	public ResponseEntity<Entry> save(@RequestBody @Valid EntryDTO entryDTO){
		
		Entry entry = this.entryService.save(entryDTO);
		
		if(entry.getCategory() == null) throw new ObjectBadRequestException("id da categoria inválido!");
		
		return ResponseEntity.status(HttpStatus.OK).body(entry);
	}
	
	@PutMapping
	public ResponseEntity<Entry> update(@RequestBody @Valid EntryDTO entryDTO){
		
		Entry entry = this.entryService.update(entryDTO);
		
		if(entry.getId() == null) throw new ObjectBadRequestException("Informe o id da entrada!");
		
		if(entry.getName() == null) throw new ObjectBadRequestException("entrada não encontrada, verifique o id informado!");
		
		return ResponseEntity.status(HttpStatus.OK).body(entry);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		boolean resp = this.entryService.delete(id);
		
		if(resp == false) throw new ObjectNotFoundException("Entrada não encontrada, verifique o id!");
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Entry>> findByName(
			@RequestParam String name, 
			@RequestParam Integer number, 
			@RequestParam Integer size){
		return null;
	}
	
	@GetMapping("/search/date")
	public ResponseEntity<Page<Entry>> findByDateBetween(
			@RequestParam Date start, 
			@RequestParam Date end, 
			@RequestParam Integer number, 
			@RequestParam Integer size){
		
		return null;
	}
	
}
