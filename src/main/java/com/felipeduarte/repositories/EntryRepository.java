package com.felipeduarte.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeduarte.models.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long>{

	public Page<Entry> findByNameAndCategory(String name, String category, Pageable page);
	
	public Page<Entry> findByDateBetween(Date start, Date end, Pageable page);
	
}
