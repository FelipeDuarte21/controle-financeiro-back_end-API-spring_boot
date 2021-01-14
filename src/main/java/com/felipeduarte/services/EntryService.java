package com.felipeduarte.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.Entry;
import com.felipeduarte.models.dtos.EntryDTO;
import com.felipeduarte.repositories.EntryRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Entry save(EntryDTO entryDTO) {
		
		Entry entry = Entry.convertEntryDTOToEntry(entryDTO);
		
		Category category = this.categoryService.findById(entryDTO.getCategoryId());
		
		if(category == null) {
			entry.setCategory(null);
			return entry;
		}
		
		entry.setCategory(category);
		
		entry = this.entryRepository.save(entry);
		
		return entry;
	}
	
	public Entry update(Entry entry) {
		return null;
	}
	
	public boolean delete(Long id) {
		return false;
	}
	
	public Page<Entry> findByNameAndCategory(String name, Category category, Integer number, Integer size){
		return null;
	}
	
	public Page<Entry> findByDateBetween(Date start, Date end, Integer number, Integer size){
		return null;
	}
		
}
