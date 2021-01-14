package com.felipeduarte.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	public Page<Entry> findByNameContaining(Long categoryId, String name, Integer number, Integer size){
		
		Category category = this.categoryService.findById(categoryId);
		
		if(category == null) return null;
		
		PageRequest page = PageRequest.of(number,size,Direction.ASC,"name");
		
		Page<Entry> pageEntry = this.entryRepository.findByCategoryAndNameContaining(category, name, page);
		
		return pageEntry;
		
	}
	
	public Page<Entry> findByDateBetween(Long categoryId, Date start, Date end, Integer number, Integer size){
		
		Category category = this.categoryService.findById(categoryId);
		
		if(category == null) return null;
		
		PageRequest page = PageRequest.of(number,size,Direction.ASC,"name");
		
		Page<Entry> pageEntry = this.entryRepository.findByCategoryAndDateBetween(category,start, end, page);
		
		return pageEntry;
	}
		
}
