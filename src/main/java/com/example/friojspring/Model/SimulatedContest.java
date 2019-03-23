package com.example.friojspring.Model;

import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="simulated_contest")
public class SimulatedContest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="simulatedContest",cascade=CascadeType.ALL)
	private Set<SimulatedSubmission> simulatedSubmissions;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="simulated_contest_problem",
		joinColumns = @JoinColumn(name="simulated_contest_id"),
		inverseJoinColumns=@JoinColumn(name="problem_id"))
	private Set<Problem> problems;
	
	@OneToMany(fetch=FetchType.LAZY , mappedBy="simulatedContest",cascade=CascadeType.ALL)
	private List<Contest> contests;

	
	
	
	
	
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

	public Set<SimulatedSubmission> getSimulatedSubmissions() {
		return simulatedSubmissions;
	}

	public void setSimulatedSubmissions(Set<SimulatedSubmission> simulatedSubmissions) {
		this.simulatedSubmissions = simulatedSubmissions;
	}

	public Set<Problem> getProblems() {
		return problems;
	}

	public void setProblems(Set<Problem> problems) {
		this.problems = problems;
	}

	public List<Contest> getContests() {
		return contests;
	}

	public void setContests(List<Contest> contests) {
		this.contests = contests;
	}
	
	
	
	
	
	
	
}
