package com.example.friojspring.Security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.friojspring.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails{

	private final Long id;
	private final String username;
	private final String password;
	private final User user;
	
	
	public JwtUser(Long id, String username, String password, User user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
		this.enabled = true;
	}
	
	
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	
	@JsonIgnore
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public User getUser() {
		return user;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public boolean isEnabled() {
		return enabled;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	

}
