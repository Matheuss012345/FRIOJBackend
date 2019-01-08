package com.example.friojspring.Security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.friojspring.Model.User;

public class JwtUserFactory {

	public static JwtUser create(User user) {
		
		return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), user, /*mapToGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE_"+user.getRole())))*/null, user.isEnabled());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map(Authority -> new SimpleGrantedAuthority(Authority)).collect(Collectors.toList());
	}

}
