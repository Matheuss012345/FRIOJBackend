package com.example.friojspring.NonEntities;

import java.util.Date;

import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.Team;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ContestRow {
	
	private Team team;
	private String teamName;	
	private String contestName;
	private Long contestId;	

	@JsonFormat(pattern="yyyy, d.MMMM HH:mm:ss")
	private Date startingTime;
	
	@JsonFormat(pattern="yyyy, d.MMMM HH:mm:ss")
	private Date endingTime;
	
	public ContestRow(Contest contest, Team team) {
		this.teamName = team.getName();
		this.contestName=contest.getName();
		this.startingTime=contest.getStartingTime();
		this.endingTime=contest.getEndingTime();
		this.team=team;
		this.contestId=contest.getId();
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getContestId() {
		return contestId;
	}

	public void setContestId(Long contestId) {
		this.contestId = contestId;
	}
	
	
	
	
	
}
