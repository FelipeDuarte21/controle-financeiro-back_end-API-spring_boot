package com.felipeduarte.services;

import java.util.Date;
import java.util.Optional;

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
	
	public Entry update(EntryDTO entryDTO) {
		
		Entry entry = Entry.convertEntryDTOToEntry(entryDTO);
		
		if(entry.getId() == null) return entry;
		
		Optional<Entry> optionalEntry = this.entryRepository.findById(entry.getId());
		
		if(optionalEntry.isEmpty()) {
			entry.setName(null);
			return entry;
		}
		
		entry.setCategory(optionalEntry.get().getCategory());
		
		entry = this.entryRepository.save(entry);
		
		return entry;
	}
	
	public boolean delete(Long id) {
		
		Optional<Entry> optionalEntry = this.entryRepository.findById(id);
		
		if(optionalEntry.isEmpty()) return false;
		
		this.entryRepository.delete(optionalEntry.get());
		
		return true;
	}
	
	public Page<Entry> findByNameAndCategory(String name, Category category, Integer number, Integer size){
		return null;
	}
	
	public Page<Entry> findByDateBetween(Date start, Date end, Integer number, Integer size){
		return null;
	}
		
}
