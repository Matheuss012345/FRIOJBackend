package com.example.friojspring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.User;
import com.example.friojspring.Repositories.SubmissionRepository;
import com.example.friojspring.Repositories.UserRepository;
import com.example.friojspring.Util.PasswordUtil;

@Service
@Transactional
public class SubmissionServiceImpl implements SubmissionService{

	@Autowired
	SubmissionRepository submissionRepository;


	@Override
	public Submission save(Submission submission) {		
		return submissionRepository.save(submission);
	}

	@Override
	public List<Submission> findByLanguage(Language language) {
		return submissionRepository.findByLanguage(language);
	}

	@Override
	public List<Submission> findByResult(Result result) {
		return submissionRepository.findByResult(result);
	}

	@Override
	public List<Submission> findAll() {
		return submissionRepository.findAll();
	}

	@Override
	public List<Submission> findByUser(User user) {
		return submissionRepository.findByUser(user);
	}

	@Override
	public List<Submission> findByUserAndResult(User user, Result result) {
		return submissionRepository.findByUserAndResult(user, result);
	}
	
	

}