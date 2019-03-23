package com.example.friojspring.NonEntities;

import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;

public class TeamRow {
	long id;
	String teamName;
	String teamCaptainName;
	String teamMember1Name;
	String teamMember2Name;
	String teamMember3Name;
	

	public TeamRow(Team team) {
		super();
		this.id = team.getId();
		this.teamName = team.getName();
		this.teamCaptainName = team.getTeamCaptain().getUsername();
		
		User[] members = new User[3];
		team.getTeamMembers().toArray(members);
		if(members[0]!=null) setTeamMember1Name(members[0].getUsername());
		if(members[1]!=null) setTeamMember2Name(members[1].getUsername());
		if(members[2]!=null) setTeamMember3Name(members[2].getUsername());
		
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamCaptainName() {
		return teamCaptainName;
	}
	public void setTeamCaptainName(String teamCaptainName) {
		this.teamCaptainName = teamCaptainName;
	}
	public String getTeamMember1Name() {
		return teamMember1Name;
	}
	public void setTeamMember1Name(String teamMember1Name) {
		this.teamMember1Name = teamMember1Name;
	}
	public String getTeamMember2Name() {
		return teamMember2Name;
	}
	public void setTeamMember2Name(String teamMember2Name) {
		this.teamMember2Name = teamMember2Name;
	}
	public String getTeamMember3Name() {
		return teamMember3Name;
	}
	public void setTeamMember3Name(String teamMember3Name) {
		this.teamMember3Name = teamMember3Name;
	}
	
	
}
