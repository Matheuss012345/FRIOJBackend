package com.example.friojspring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.RanklistRow;

public interface SubmissionService {
	Submission save(Submission submission);
	Submission createNewSubmission(long problemID,String code,long languageID,Date submissionTime,String username);
	Submission createNewSubmission(long problemID,String code,long languageID,Date submissionTime,String username, long contestId, long teamId);
	Submission createNewSubmission(long problemID,String code,long languageID,Date submissionTime,String username, long exerciseId);
	
	List<Submission> findAll();

	List<Submission> findByLanguage(Language language);
	List<Submission> findByResult(Result result);
	
	List<Submission> findByUsername(String username);
	List<Submission> findByUser(User user);
	List<Submission> findByUserAndResult(User user, Result result);

	List<SubmissionDTO> getAllSubmissionDTOsForUser(String username);
	List<SubmissionDTO> getAllSubmissionDTOs();
	SubmissionDTO getSubmissionDTO(long submissionId);
	
	List<SubmissionDTO> getSubmissions(List<Long> userIDs,List<Long> problemsIDs,Date from, Date till);
	List<RanklistRow> getAllRankListRows();
	
}
