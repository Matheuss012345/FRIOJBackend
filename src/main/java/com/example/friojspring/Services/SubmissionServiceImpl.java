package com.example.friojspring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Constants.Constants;
import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.Exercise;
import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.RanklistRow;
import com.example.friojspring.Repositories.SubmissionRepository;

@Service
@Transactional
public class SubmissionServiceImpl implements SubmissionService{

	@Autowired
	SubmissionRepository submissionRepository;
	
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResultService resultService;

	@Autowired
	private ContestService contestService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private ExerciseService exerciseService;

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

	@Override
	public Submission createNewSubmission(long problemID,String code, long languageID,Date submissionTime, String username) {
				
		Submission submission = new Submission(
				problemService.findById(problemID),
				userService.getUserByUsername(username), 
				code,
				languageService.findById(languageID),
				resultService.findByCode("NE"),  // not evaluated
				submissionTime
		);
	
		return submission;
	}
	
	public Submission createNewSubmission(long problemID,String code, long languageID,Date submissionTime, String username, long contestId, long teamId) {
		
		Contest contest = contestService.getContest(contestId);
		Team team = teamService.getTeam(teamId);
		
		Submission submission = new Submission(
				problemService.findById(problemID),
				userService.getUserByUsername(username), 
				code,
				languageService.findById(languageID),
				resultService.findByCode("NE"),  // not evaluated
				submissionTime,
				contest,
				team			
		);		
		return submission;
	}

	public Submission createNewSubmission(long problemID,String code, long languageID,Date submissionTime, String username, long exerciseId) {
		
		Exercise exercise = exerciseService.getExercise(exerciseId);
		
		Submission submission = new Submission(
				problemService.findById(problemID),
				userService.getUserByUsername(username), 
				code,
				languageService.findById(languageID),
				resultService.findByCode("NE"),  // not evaluated
				submissionTime,
				exercise			
		);		
		return submission;
	}
	
	@Override
	public List<Submission> findByUsername(String username) {
		User user = userService.getUserByUsername(username);
		
		return findByUser(user);
	}

	@Override
	public List<SubmissionDTO> getAllSubmissionDTOsForUser(String username) {
		return submissionRepository.getAllSubmissionsForUser(username);
	}

	@Override
	public List<SubmissionDTO> getAllSubmissionDTOs() {
		return submissionRepository.getAllSubmissions();
	}

	@Override
	public SubmissionDTO getSubmissionDTO(long submissionId) {
		return submissionRepository.getSubmissionDTO(submissionId);
	}

	@Override
	public List<SubmissionDTO> getSubmissions(List<Long> userIDs, List<Long> problemsIDs, Date from, Date till) {
		return submissionRepository.getSubmissions(userIDs, problemsIDs, from, till);
	}

	@Override
	public List<RanklistRow> getAllRankListRows() {
		List<SubmissionDTO> allSubmissions = submissionRepository.getAllSubmissionsForRanklist();
		HashMap<String,RanklistRow> map = new HashMap<>();
		
		String name;
		for (SubmissionDTO sub : allSubmissions) {
			name=sub.getUsername();
			
			if(!map.containsKey(name)) {
				map.put(name, new RanklistRow(name));
			}

			if(sub.getResultId()==Constants.ACCEPTED_ID) {
				map.get(name).addProblemSolved(sub.getProblemID());
			}
			
			map.get(name).addSubmission();				
			
		}
		
		List<RanklistRow> res = new ArrayList<>(map.keySet().size());
		for (RanklistRow ranklistRow : map.values()) {
			res.add(ranklistRow);
		}
		
		res.sort(RanklistRow.scoreComparator);
		
		return res;
	}


	
	

}