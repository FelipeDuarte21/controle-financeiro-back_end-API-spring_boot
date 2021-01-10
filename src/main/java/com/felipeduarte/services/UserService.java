package com.felipeduarte.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.User;

@Service
public class UserService {
	
	public User save(User user) {
		return null;
	}
	
	public User update(User user) {
		return null;
	}
	
	public boolean delete(Long id) {
		return false;
	}
	
	public User findById(Long id) {
		return null;
	}
	
	public Page<User> findByNome(String nome, int number, int size){
		return null;
	}
	
	public Page<User> findAll(int number, int size){
		return null;
	}
	
}
