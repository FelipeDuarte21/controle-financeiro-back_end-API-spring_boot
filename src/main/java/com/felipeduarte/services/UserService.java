package com.felipeduarte.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.User;
import com.felipeduarte.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	public Page<User> findByNome(String nome, Integer number, Integer size){
		return null;
	}
	
	public Page<User> findAll(Integer number, Integer size){
		return null;
	}
	
}
