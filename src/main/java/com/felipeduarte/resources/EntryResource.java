package com.felipeduarte.resources;

import java.util.Date;

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

import com.felipeduarte.models.Entry;

@CrossOrigin
@RestController
@RequestMapping("/entry")
public class EntryResource {
	
	@PostMapping
	public ResponseEntity<Entry> save(@RequestBody @Valid Entry entry){
		return null;
	}
	
	@PutMapping
	public ResponseEntity<Entry> update(@RequestBody @Valid Entry entry){
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return null;
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
