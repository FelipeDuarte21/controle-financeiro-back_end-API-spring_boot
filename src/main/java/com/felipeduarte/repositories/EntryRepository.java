package com.felipeduarte.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeduarte.models.Category;
import com.felipeduarte.models.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long>{

	public Page<Entry> findByCategoryAndNameContaining(Category category, String name, Pageable page);
	
	public Page<Entry> findByCategoryAndDateBetween(Category category, Date start, Date end, Pageable page);
	
}
