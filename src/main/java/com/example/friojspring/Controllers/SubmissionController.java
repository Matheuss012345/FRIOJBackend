package com.example.friojspring.Controllers;

	
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.friojspring.Domain.Response;
import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.NonEntities.RanklistRow;
import com.example.friojspring.Services.LanguageService;
import com.example.friojspring.Services.ProblemService;
import com.example.friojspring.Services.ResultService;
import com.example.friojspring.Services.SubmissionService;
import com.example.friojspring.Services.UserService;

@RestController
@RequestMapping(value="/submissions")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResultService resultService;
	
	@PostMapping(value="/submit")
	public ResponseEntity<Submission> submit(@RequestBody SubmissionDTO submissionDTO){
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();	
		//String username="Matheuss";  //-->> only for now
		
		Submission submission = submissionService.createNewSubmission(
				submissionDTO.getProblemID(), 
				submissionDTO.getCode(),
				submissionDTO.getLanguageID(),
				submissionDTO.getSubmissionTime(),
				username
		);
		submission=submissionService.save(submission);
	
		return new ResponseEntity<Submission>(HttpStatus.OK);
	}
	
	@PostMapping(value="/contestSubmit")
	public ResponseEntity<Submission> contestSubmit(@RequestBody SubmissionDTO submissionDTO){
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();	
		
		Submission submission = submissionService.createNewSubmission(
				submissionDTO.getProblemID(), 
				submissionDTO.getCode(),
				submissionDTO.getLanguageID(),
				submissionDTO.getSubmissionTime(),
				username,
				submissionDTO.getContestId(),
				submissionDTO.getTeamId()
		);
		submission=submissionService.save(submission);
	
		return new ResponseEntity<Submission>(HttpStatus.OK);
	}
	
	@PostMapping(value="/exerciseSubmit")
	public ResponseEntity<Submission> exerciseSubmit(@RequestBody SubmissionDTO submissionDTO){
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();	
		
		Submission submission = submissionService.createNewSubmission(
				submissionDTO.getProblemID(), 
				submissionDTO.getCode(),
				submissionDTO.getLanguageID(),
				submissionDTO.getSubmissionTime(),
				username,
				submissionDTO.getExerciseId()
		);
		submission=submissionService.save(submission);
	
		return new ResponseEntity<Submission>(HttpStatus.OK);
	}
	
	@GetMapping(value="/mySubmissions")
	public ResponseEntity<List<SubmissionDTO>> getAllMySubmissions(){
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();	
		//String username="Matheuss";  //-->> only for now
		
		List submissions = submissionService.getAllSubmissionDTOsForUser(username);
	
		return new ResponseEntity<List<SubmissionDTO>>(submissions,HttpStatus.OK);
	}
	
	@GetMapping(value="/allSubmissions")
	public ResponseEntity<List<SubmissionDTO>> getAllSubmissions(){
		
		List<SubmissionDTO> submissions = submissionService.getAllSubmissionDTOs();
	
		return new ResponseEntity<List<SubmissionDTO>>(submissions,HttpStatus.OK);
	}
	
	@GetMapping(value="/submission/{submissionId}")
	public ResponseEntity<SubmissionDTO> getSubmissionDTO(@PathVariable("submissionId") long submissionId){
		
		SubmissionDTO submission = submissionService.getSubmissionDTO(submissionId);
		System.out.println("sub id "+submissionId);
		System.out.println("res "+submission);
	
		return new ResponseEntity<SubmissionDTO>(submission,HttpStatus.OK);
	}	
	
	@GetMapping(value="/ranklist")
	public ResponseEntity<List<RanklistRow>> getRanklist(){

		List<RanklistRow> ranklist = submissionService.getAllRankListRows();
		
		return new ResponseEntity<List<RanklistRow>>(ranklist,HttpStatus.OK);
	}
	
}
