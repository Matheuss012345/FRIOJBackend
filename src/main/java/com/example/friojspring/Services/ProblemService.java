package com.example.friojspring.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.UserProblem;
import com.example.friojspring.NonEntities.UserProblem;

public interface ProblemService {
	Problem findById(long id);
	List<Problem> findByName(String name);
	List<Problem> findAll();
	Problem save(Problem problem);
	Page<Problem> findVisibleProblems(Pageable pageable);
	Page<Problem> findAll(Pageable pageable);
	
	//Table problems for user
	List<UserProblem> getAllProblemRowsForUser(User user);
	List<UserProblem> getVisibleUserProblems(User user,Pageable pageable);
	UserProblem getUserProblem(String username, long problemId);
	
	//PDF and TXT files
	byte[] getPdf(long id);
	byte[] getInput(long id);
	byte[] getOutput(long id);
	
	//
}
