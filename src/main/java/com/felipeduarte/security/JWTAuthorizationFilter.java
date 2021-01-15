package com.felipeduarte.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	private JWTUtil jwtUtil;
	
	private UserDetailsServiceAdapter userDetailsServiceAdapter;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, 
			UserDetailsServiceAdapter userDetailsServiceAdapter) {
		super(authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsServiceAdapter = userDetailsServiceAdapter;
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer ")){
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(this.jwtUtil.tokenValido(token)) {
			String id = this.jwtUtil.getId(token);
			UserDetailsAdapter userDetailsAdapter = this.userDetailsServiceAdapter.loadUserById(Long.parseLong(id));
			return  new UsernamePasswordAuthenticationToken(userDetailsAdapter,null,
					userDetailsAdapter.getAuthorities());
		}
		return null;
	}

}
