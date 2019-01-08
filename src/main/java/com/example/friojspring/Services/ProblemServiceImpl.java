package com.example.friojspring.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.Tag;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.UserProblem;
import com.example.friojspring.NonEntities.UserProblem;
import com.example.friojspring.Repositories.ProblemRepository;
import com.example.friojspring.Repositories.ResultRepository;
import com.example.friojspring.Repositories.SubmissionRepository;

@Service
@Transactional
public class ProblemServiceImpl implements ProblemService{

	@Autowired
	ProblemRepository problemRepository;
	
	@Autowired
	SubmissionRepository submissionRepository;
	
	@Autowired
	ResultRepository resultRepository;
	
	@Autowired
	TagService tagService;
	
	@Override
	public Problem findById(long id) {
		Optional<Problem> result = problemRepository.findById(id);
		return result.isPresent()?result.get():null;
	}

	@Override
	public List<Problem> findByName(String name) {
		return problemRepository.findByName(name);
	}

	@Override
	public List<Problem> findAll() {
		return problemRepository.findAll();
	}

	@Override
	public Problem save(Problem problem) {
		return problemRepository.save(problem);
	}

	@Override
	public List<UserProblem> getAllProblemRowsForUser(User user) {
		return null;
		
	}


	@Transactional
	@Override
	public Page<Problem> findVisibleProblems(Pageable pageable) {
		return problemRepository.findVisibleProblems(pageable);

	}

	@Override
	public Page<Problem> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Transactional
	@Override
	public List<UserProblem> getVisibleUserProblems(User user, Pageable pageable) {
		List<Problem> problems = findVisibleProblems(pageable).getContent();
		//System.out.println("number of problems="+problems.size());
		List<UserProblem> rows = new ArrayList<>(problems.size());
		
		// 1 mapping selected data from problem to row
		for (Problem p : problems) {
			UserProblem ptr = new UserProblem(p.getId(), p.getName(), p.getTags(),p.getTimelimit());
			rows.add(ptr);
		}
		//System.out.println("number of rows1="+rows.size());
		
		// 2 setting solved for users problems
		Set<Long> solvedProblemIdsForUser = problemRepository.findIdsOfAllSolvedProblemsOfUser(user.getUsername());
		for (UserProblem ptr : rows) {
			if(solvedProblemIdsForUser.contains(ptr.getProblemId())) {
				ptr.setSolved(true);
			}
		}
		//System.out.println("number of rows2="+rows.size());
		
		// 3 adding statistics to problem
		for (UserProblem ptr : rows) {
			ptr.setSubmissionsSolved(problemRepository.getNumberOfAllAcceptedSubmissionsOfThisProblem(ptr.getProblemId()));
			ptr.setSubmissionsTried(problemRepository.getNumberOfAllSubmissionsOfThisProblem(ptr.getProblemId()));
			ptr.setUsersSolved(problemRepository.getNumberOfUsersWhoSolvedThisProblem(ptr.getProblemId()));
		  	ptr.setUsersTried(problemRepository.getNumberOfUsersWhoTriedThisProblem(ptr.getProblemId()));
		}
		//System.out.println("number of rows3="+rows.size());
		
		return rows;
	}
	
	@Transactional
	@Override
	public UserProblem getUserProblem(String username, long problemId) {
		Problem p =findById(problemId);
		UserProblem userProblem = new UserProblem(p.getId(), p.getName(), p.getTags(), p.getTimelimit());
		
		// 2 setting solved for user problem
		boolean solved;
		solved= problemRepository.getNumerOfAcceptedSubmissionOfThisProblemForThisUser(problemId, username)>0?true:false;
		userProblem.setSolved(solved);
		
		// 3 adding statistics to problem
		userProblem.setSubmissionsSolved(problemRepository.getNumberOfAllAcceptedSubmissionsOfThisProblem(userProblem.getProblemId()));
		userProblem.setSubmissionsTried(problemRepository.getNumberOfAllSubmissionsOfThisProblem(userProblem.getProblemId()));
		userProblem.setUsersSolved(problemRepository.getNumberOfUsersWhoSolvedThisProblem(userProblem.getProblemId()));
		userProblem.setUsersTried(problemRepository.getNumberOfUsersWhoTriedThisProblem(userProblem.getProblemId()));
		  	
		return userProblem;
	}

	@Override
	public byte[] getPdf(long id) {
		return problemRepository.findPdfById(id);
	}

	@Override
	public byte[] getInput(long id) {
		return problemRepository.findInputById(id);
	}

	@Override
	public byte[] getOutput(long id) {
		return problemRepository.findOutputById(id);
	}



	
	
	

}
