package com.example.friojspring.Services;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Exceptions.EmailTakenException;
import com.example.friojspring.Exceptions.UsernameTakenException;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.User;

public interface UserService {

	@Transactional
	User save(User user) throws UsernameTakenException, EmailTakenException;

	List<User> findAll();

	User getUserByEmail(String email);
	User getUserByUsername(String userName);
	
	//List<User> getAllUsersWhoTriedProblem(Problem problem);
	//long getNumberOfUsersWhoTriedProblem(Problem problem);
	
	List<User> getAllUsersWhoSolvedThisProblem(Problem problem);
	//long getNumberOfUsersWhoSolvedProblem(Problem problem);
	
	boolean existsUserWithThisEmail(String email);
	boolean existsUserWithThisUsername(String username);

}
