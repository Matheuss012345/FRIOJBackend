package com.example.friojspring.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Simulated_submission")
public class SimulatedSubmission {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private SimulatedContest simulatedContest;
	
	@JsonIgnore
	@ManyToOne
	private Problem problem;

	private long submissionTimeInMs; // in miliseconds from start of the contest
	
	private String team;
	
	private long result;
	
	
	
	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimulatedContest getSimulatedContest() {
		return simulatedContest;
	}

	public void setSimulatedContest(SimulatedContest simulatedContest) {
		this.simulatedContest = simulatedContest;
	}

	public long getSubmissionTimeInMs() {
		return submissionTimeInMs;
	}

	public void setSubmissionTimeInMs(long submissionTime) {
		this.submissionTimeInMs = submissionTimeInMs;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	
}
