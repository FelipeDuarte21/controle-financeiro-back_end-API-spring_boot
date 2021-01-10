package com.felipeduarte.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.Entry;
import com.felipeduarte.repositories.EntryRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	public Entry save(Entry entry) {
		return null;
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
