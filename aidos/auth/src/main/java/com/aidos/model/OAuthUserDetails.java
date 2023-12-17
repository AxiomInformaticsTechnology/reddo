package com.aidos.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OAuthUserDetails implements UserDetails {

	private static final long serialVersionUID = 7079404062697850654L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
	private String username;

	@JsonIgnore
	@Column(nullable = true)
	private String password;

	@Column(length = 20, nullable = false)
	private String role;

	@Column(nullable = false)
	private boolean active;

	public OAuthUserDetails() {

	}

	public OAuthUserDetails(String username) {
		this();
		setUsername(username);
	}

	public OAuthUserDetails(String username, String password) {
		this(username);
		setPassword(password);
	}
	
	public OAuthUserDetails(String username, String role, boolean active) {
		this(username);
		setRole(role);
		setActive(active);
	}

	public OAuthUserDetails(String username, String password, String role) {
		this(username, password);
		setRole(role);
	}

	public OAuthUserDetails(String username, String password, String role, boolean active) {
		this(username, password, role);
		setActive(active);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

}
