package com.felipeduarte.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.Entry;

@Service
public class EntryService {
	
	public Entry save(Entry entry) {
		return null;
	}
	
	public Entry update(Entry entry) {
		return null;
	}
	
	public boolean delete(Long id) {
		return false;
	}
	
	public Page<Entry> findByNameAndCategory(String name, Category category, int number, int size){
		return null;
	}
	
	public Page<Entry> findByDateBetween(Date start, Date end, int number, int size){
		return null;
	}
		
}
