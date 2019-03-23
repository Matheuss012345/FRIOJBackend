package com.example.friojspring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.ContestTable;
import com.example.friojspring.NonEntities.MyContests;
import com.example.friojspring.Services.ContestService;
import com.example.friojspring.Services.UserService;

@RestController
@RequestMapping(value="/contests")
public class ContestController {
	
	@Autowired ContestService contestService;
	
	@Autowired UserService userService;
	
	@GetMapping(value="/contest/{contestId}/contestTable")
	public ResponseEntity<ContestTable> getContestTable(@PathVariable Long contestId){
		ContestTable table = contestService.getContestTable(contestId);
		return new ResponseEntity<ContestTable>(table,HttpStatus.OK);
	}
	
	@GetMapping(value="/myContests")
	public ResponseEntity<MyContests> getMyContests(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.getUserByUsername(username);
		
		MyContests myContests = contestService.getMyContests(currentUser);
		return new ResponseEntity<MyContests>(myContests,HttpStatus.OK);
	}
	
	@GetMapping(value="/contest/{contestId}")
	public ResponseEntity<Contest> getContest(@PathVariable Long contestId){
		
		Contest contest = contestService.getContest(contestId);
		return new ResponseEntity<Contest>(contest,HttpStatus.OK);
	}
	
	
}
