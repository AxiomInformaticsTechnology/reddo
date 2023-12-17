package com.aidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aidos.model.OAuthUserDetails;
import com.aidos.model.repo.OAuthUserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private OAuthUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		OAuthUserDetails user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		if (!user.isActive()) {
			throw new LockedException("Account not active");
		}
		return user;
	}

}
