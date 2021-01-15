package com.felipeduarte.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipeduarte.models.User;
import com.felipeduarte.repositories.UserRepository;
import com.felipeduarte.services.UserService;

@Service
public class UserDetailsServiceAdapter implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = this.userRepository.findByEmail(email);
		
		if(optionalUser.isEmpty()) throw new UsernameNotFoundException(email);;
		
		User user = optionalUser.get();
		
		UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user.getId(),user.getName(),
				user.getEmail(),user.getPassword(),user.getTypes());
		
		return userDetailsAdapter;
	}
	
	public UserDetailsAdapter loadUserById(Long id) {
		
		User user = this.userService.findById(id,false);
		
		if(user == null) return null;
		
		UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user.getId(),user.getName(),
				user.getEmail(),user.getPassword(),user.getTypes());
		
		return userDetailsAdapter;
	}
	
	public static UserDetailsAdapter getUser() {
		try {
			return (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		}catch(Exception e) {
			return null;
		}
	}

}
