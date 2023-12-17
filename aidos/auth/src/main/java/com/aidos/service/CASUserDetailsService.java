package com.aidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aidos.model.OAuthUserDetails;
import com.aidos.model.repo.OAuthUserRepo;

@Service
public class CASUserDetailsService implements AuthenticationUserDetailsService<Authentication> {

	@Autowired
	private OAuthUserRepo userRepo;

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		OAuthUserDetails user = userRepo.findByUsername(token.getName());
		if (user == null) {
			user = userRepo.save(new OAuthUserDetails(token.getName(), "ROLE_USER", true));
		}
		if (!user.isActive()) {
			throw new LockedException("Account not active");
		}
		return user;
	}

}
