package com.example.friojspring.Controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Domain.UserDTO;
import com.example.friojspring.Exceptions.UnauthorizedException;
import com.example.friojspring.Model.User;
import com.example.friojspring.Security.JwtTokenUtil;
import com.example.friojspring.Security.JwtUser;

@RestController
public class AuthenticationController {
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(value="/login")
	public ResponseEntity<UserDTO> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		try {
			System.out.println("name"+SecurityContextHolder.getContext().getAuthentication().getName());
			System.out.println("Was here 1?");
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
			System.out.println("Was here 2?");
			Authentication authentication = authenticationManager.authenticate(authToken);
			System.out.println("Was here 3?");
			final JwtUser userDetails = (JwtUser)authentication.getPrincipal();
			System.out.println("Was here 4?");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("Was here 5?");
			final String token = jwtTokenUtil.generateToken(userDetails);
			System.out.println("Was here 6?");
			System.out.println("token "+token);
			response.setHeader("Token", token);
			System.out.println("name"+SecurityContextHolder.getContext().getAuthentication().getName());
			return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getUser(),token),HttpStatus.OK);
			
		}catch(Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
		
	
}
