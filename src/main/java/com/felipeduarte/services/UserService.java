package com.felipeduarte.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.User;
import com.felipeduarte.models.dtos.UserDTO;
import com.felipeduarte.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		
		Optional<User> optionalUser = this.userRepository.findByEmail(user.getEmail());
		
		if(optionalUser.isPresent()) return null;
		
		user = this.userRepository.save(user);
		
		return user;
	}
	
	public UserDTO update(UserDTO userDTO) {
		
		if(userDTO.getId() == null) {
			userDTO.setId(null);
			return userDTO;
		}
		
		Optional<User> optionalUser = this.userRepository.findById(userDTO.getId());
		
		if(!optionalUser.isPresent()) {
			userDTO.setName(null);
			return userDTO;
		}
		
		User user = User.convertUserDTOToUser(userDTO);
		
		user.setPassword(optionalUser.get().getPassword());
		
		user = this.userRepository.save(user);
		
		userDTO = User.convertUserToUserDTO(user);
		
		return userDTO;
	}
	
	public boolean delete(Long id) {
		
		User user = this.findById(id);
		
		if(user == null) return false;
		
		this.userRepository.delete(user);
		
		return true;
	}
	
	public User findById(Long id) {
		
		Optional<User> optionalUser = this.userRepository.findById(id);
		
		if(!optionalUser.isPresent()) return null;
		
		return optionalUser.get();
	}
	
	public Page<User> findByNome(String name, Integer number, Integer size){
		
		PageRequest page = PageRequest.of(number,size, Direction.ASC, "name");
		
		Page<User> pageUser;
		
		if(!name.isEmpty()) {
			pageUser = this.userRepository.findByNameContaining(name, page);
		}else {
			pageUser = this.findAll(number, size);
		}
		
		return pageUser;
	}
	
	public Page<User> findAll(Integer number, Integer size){
		
		PageRequest page = PageRequest.of(number, size, Direction.ASC, "name");
		
		Page<User> pageUser = this.userRepository.findAll(page);
		
		return pageUser;
	}
	
}
