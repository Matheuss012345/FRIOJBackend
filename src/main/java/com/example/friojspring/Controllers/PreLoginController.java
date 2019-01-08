package com.example.friojspring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Domain.Response;
import com.example.friojspring.Exceptions.EmailTakenException;
import com.example.friojspring.Exceptions.UserCreationException;
import com.example.friojspring.Exceptions.UsernameTakenException;
import com.example.friojspring.Model.User;
import com.example.friojspring.Services.UserService;

@RestController
public class PreLoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/registration")
	public ResponseEntity<Response> registration(@RequestBody User user) throws Exception{
		
		//check if this username already exists
		boolean usernameTaken = userService.existsUserWithThisUsername(user.getUsername());
		if(usernameTaken) {
			throw new UsernameTakenException();
		}
		
		//check if this email already exists
		boolean emailTaken = userService.existsUserWithThisEmail(user.getEmail());
		if(emailTaken) {
			throw new EmailTakenException();
		}
		
		try {
			User createdUser = userService.save(user);
		}catch(Exception e) {
			throw e;
		}
		
		return new ResponseEntity<Response>(new Response("User has been successfuly registered."),HttpStatus.OK);

	}
	
	
	@PostMapping(value="/checkEmail")
	public ResponseEntity<Boolean> checkEmail(@RequestBody User user){
		
		Boolean result = userService.existsUserWithThisEmail(user.getEmail());
		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
	}
	
	@PostMapping(value="/checkUsername")
	public ResponseEntity<Boolean> checkUsername(@RequestBody User user){
		
		Boolean result = userService.existsUserWithThisUsername(user.getUsername());
		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
	}	
}
