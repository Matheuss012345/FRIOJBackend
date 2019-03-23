package com.example.friojspring.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Contest")
public class Contest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Date startingTime;
	
	private Date endingTime;

	
	//@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="contest_problem",
		joinColumns = @JoinColumn(name="contest_id"),
		inverseJoinColumns=@JoinColumn(name="problem_id"))
	private List<Problem> problems;
	
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="contest_team",
		joinColumns = @JoinColumn(name="contest_id"),
		inverseJoinColumns=@JoinColumn(name="team_id"))
	private List<Team> teams;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY , mappedBy="contest",cascade=CascadeType.ALL)
	private List<Submission> submissions;
	
	@JsonIgnore
	@ManyToOne
	private SimulatedContest simulatedContest;

	public Contest() {
		
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Problem> getProblems() {
		return problems;
	}

	public void setProblems(List<Problem> problems) {
		this.problems = problems;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}



	public SimulatedContest getSimulatedContest() {
		return simulatedContest;
	}



	public void setSimulatedContest(SimulatedContest simulatedContest) {
		this.simulatedContest = simulatedContest;
	}

	public void addSubmission(Submission s) {
		submissions.add(s);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
