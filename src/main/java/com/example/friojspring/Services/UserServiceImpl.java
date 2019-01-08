package com.example.friojspring.Services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.User;
import com.example.friojspring.Repositories.UserRepository;
import com.example.friojspring.Util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user) {
		String password = PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		user.setCreatedDate(new Date());
		
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUsersWhoSolvedThisProblem(Problem problem) {
		List<User> res =  userRepository.findAllUsersWhoSolvedThisProblem(problem.getId());
		System.out.println("size" + res.size());
		for (User user : res) {
			System.out.println(user.getUsername());
		}
		return res;
	}

	@Override
	public boolean existsUserWithThisEmail(String email) {
		return userRepository.getNumberOfUsersWithThisEmail(email)==1;
	}

	@Override
	public boolean existsUserWithThisUsername(String username) {
		return userRepository.getNumberOfUsersWithThisUsername(username)==1;
	}


	
}
