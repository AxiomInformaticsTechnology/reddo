package com.aidos.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.aidos.model.OAuthUserDetails;
import com.aidos.model.repo.OAuthUserRepo;

@Component
public class ApplicationInitializer implements CommandLineRunner {

	@Autowired
	private OAuthUserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		userRepo.save(new OAuthUserDetails("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN", true));
	}

}