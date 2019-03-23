package com.example.friojspring.Services;

import java.util.List;
import java.util.Set;

import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.TeamRow;

public interface TeamService {

	List<Team> getAllTeams();
	List<Team> getAllUserTeams(String username);
	
	void createTeam(String name,User teamCaptain, Set<User> members);
	
	
	List<TeamRow> getAllTeamRows();
	List<TeamRow> getAllUserTeamRows(String username);
	
	void removeTeam(long teamId);
	Team getTeam(long teamId);
}
