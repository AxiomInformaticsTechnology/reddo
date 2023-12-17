package com.aidos.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aidos.model.OAuthUserDetails;

public interface OAuthUserRepo extends JpaRepository<OAuthUserDetails, Long> {

	public OAuthUserDetails findByUsername(String username);

}
