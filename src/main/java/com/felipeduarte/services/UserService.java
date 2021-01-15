package com.felipeduarte.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipeduarte.exceptions.AuthorizationException;
import com.felipeduarte.models.User;
import com.felipeduarte.models.dtos.UserDTO;
import com.felipeduarte.models.dtos.UserDTOWithPassword;
import com.felipeduarte.repositories.UserRepository;
import com.felipeduarte.security.UserDetailsAdapter;
import com.felipeduarte.security.UserDetailsServiceAdapter;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEnconder;
	
	public User save(UserDTOWithPassword userDTOWithPassword) {
		
		Optional<User> optionalUser = this.userRepository.findByEmail(userDTOWithPassword.getEmail());
		
		if(optionalUser.isPresent()) return null;
		
		User user = User.convertUserDTOWithPasswordToUser(userDTOWithPassword);
		
		user.setPassword(this.bCryptPasswordEnconder.encode(user.getPassword()));
		
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
		
		UserServicePermission.verifyPermissionUserUpdate(userDTO.getId());
		
		User user = User.convertUserDTOToUser(userDTO);
		
		user.setPassword(optionalUser.get().getPassword());
		
		user = this.userRepository.save(user);
		
		userDTO = User.convertUserToUserDTO(user);
		
		return userDTO;
	}
	
	public boolean delete(Long id) {
		
		User user = this.findById(id,true);
		
		if(user == null) return false;
		
		UserServicePermission.verifyPermissionUserDelete(id);
		
		this.userRepository.delete(user);
		
		return true;
	}
	
	public User findById(Long id, boolean verificaPermissao) {
		
		Optional<User> optionalUser = this.userRepository.findById(id);
		
		if(!optionalUser.isPresent()) return null;
		
		if(verificaPermissao) UserServicePermission.verifyUserId(id);
		
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
	
	private static class UserServicePermission{
		
		public static void verifyPermissionUserUpdate(Long id){
			
			UserDetailsAdapter user = UserDetailsServiceAdapter.getUser();
			
			if(user == null || !user.getId().equals(id))
				throw new AuthorizationException("Usuario não tem permissão para "
						+ "alterar dados dos outros usuarios");
		}
		
		public static void verifyPermissionUserDelete(Long id) {
			
			UserDetailsAdapter user = UserDetailsServiceAdapter.getUser();
			
			if(user == null || (user.hasRole("ROLE_USER") && !user.getId().equals(id)) ) {
				throw new AuthorizationException("Usuário não tem permissão para" 
					+ "excluir outros usuários");
			}
		
		}
		
		public static void verifyUserId(Long id) {
			
			UserDetailsAdapter user = UserDetailsServiceAdapter.getUser();
			
			if(user == null) throw new AuthorizationException("Usuario não logado!");
			
			if(!user.getId().equals(id)) throw new AuthorizationException("Este id não pertence ao usuario logado!");
			
		}
		
	}
	
}
