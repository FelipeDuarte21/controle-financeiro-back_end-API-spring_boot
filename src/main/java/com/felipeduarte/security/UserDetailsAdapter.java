package com.felipeduarte.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.felipeduarte.models.enums.UserType;

public class UserDetailsAdapter implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsAdapter(Long id, String name, String email, String password, Set<Integer> types) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.authorities = types.stream().map(
				t -> new SimpleGrantedAuthority(UserType.toEnum(t).getDescription()) )
					.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(String role) {
		return this.authorities.contains(new SimpleGrantedAuthority(role));
	}

}
