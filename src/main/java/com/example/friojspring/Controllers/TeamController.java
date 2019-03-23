package com.example.friojspring.Controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Model.Team;
import com.example.friojspring.NonEntities.TeamRow;
import com.example.friojspring.Services.TeamService;

@RestController
@RequestMapping(value="/teams")
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@GetMapping(value="/allTeams")
	public ResponseEntity<List<TeamRow>> getAllTeamRows(){
		List<TeamRow> allTeamRows = teamService.getAllTeamRows();
		return new ResponseEntity<List<TeamRow>>(allTeamRows,HttpStatus.OK);
	}
	
	@GetMapping(value="/myTeams")
	public ResponseEntity<List<TeamRow>> getAllUserTeamRows(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//String username="Matheuss";
		List<TeamRow> myTeamRows = teamService.getAllUserTeamRows(username);
		return new ResponseEntity<List<TeamRow>>(myTeamRows,HttpStatus.OK);
	}
	
	@PostMapping(value="/createTeam")
	public ResponseEntity<Void> createTeam(@RequestBody Team team){
		teamService.createTeam(team.getName(),team.getTeamCaptain(), team.getTeamMembers());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value="/removeTeam/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable long id){
		teamService.removeTeam(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/team/{teamId}")
	public ResponseEntity<Team> getTeam(@PathVariable long teamId){
		Team team = teamService.getTeam(teamId);
		return new ResponseEntity<Team>(team,HttpStatus.OK);
	}
}
