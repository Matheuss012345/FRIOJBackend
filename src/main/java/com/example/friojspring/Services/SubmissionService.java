package com.example.friojspring.Services;

import java.util.List;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.User;

public interface SubmissionService {
	Submission save(Submission submission);

	List<Submission> findAll();

	List<Submission> findByLanguage(Language language);
	List<Submission> findByResult(Result result);
	
	List<Submission> findByUser(User user);
	List<Submission> findByUserAndResult(User user, Result result);

}
