package com.example.friojspring.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.TeamRow;
import com.example.friojspring.Repositories.TeamRepository;
import com.example.friojspring.Repositories.UserRepository;

@Service
@Transactional
public class TeamServiceImpl implements TeamService{

	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@Override
	public List<Team> getAllUserTeams(String username) {
		User user = userRepository.findByUsername(username);
		
		return teamRepository.findAllUserTeams(user.getId());
	}

	@Override
	public void createTeam(String name,User teamCaptain, Set<User> members) {
		if(members.size()>3) return;
		
		Team team = new Team(name);
		team.setTeamCaptain(teamCaptain);

		Set<User> membersToAdd = new HashSet<>();
		for (User user : members) {
			membersToAdd.add(userRepository.findByUsername(user.getUsername()));
		}
		team.setTeamMembers(membersToAdd);
		teamRepository.save(team);	
	}

	@Override
	public List<TeamRow> getAllTeamRows() {
		List<Team> teams = getAllTeams();
		List<TeamRow> teamRows = new ArrayList<>(teams.size());
		
		for (Team t : teams) {
			TeamRow tr = new TeamRow(t);
			teamRows.add(tr);
			
		}
		return teamRows;
	}

	@Override
	public List<TeamRow> getAllUserTeamRows(String username) {
		List<Team> teams = getAllUserTeams(username);
		List<TeamRow> teamRows = new ArrayList<>(teams.size());
		
		for (Team t : teams) {
			TeamRow tr = new TeamRow(t);
			teamRows.add(tr);
			
		}
		return teamRows;
	}

	@Override
	public void removeTeam(long teamId) {
		teamRepository.deleteAllTeamMembers(teamId);
		teamRepository.deleteTeamById(teamId);
	}

	@Override
	public Team getTeam(long teamId) {
		return teamRepository.getOne(teamId);
	}
	
	

}
