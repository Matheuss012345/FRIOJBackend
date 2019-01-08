package com.example.friojspring.NonEntities;

import java.util.Set;
import java.util.TreeSet;

import com.example.friojspring.Model.Tag;

public class UserProblem {
	
	private long problemId;
	private String problemName;
	private Set<String> tags;
	private boolean solved;
	private long timelimit;
	
	private int usersTried;
	private int usersSolved;
	
	private int submissionsTried;
	private int submissionsSolved;
	

	public UserProblem(long id, String problemName, Set<Tag> tags , long timelimit) {
		super();
		this.problemId = id;
		this.problemName = problemName;
		this.timelimit=timelimit;
		
		this.tags=new TreeSet<String>();
		for (Tag tag : tags) {
			this.tags.add(tag.getName());
		}
	}
	public long getProblemId() {
		return problemId;
	}
	public void setProblemId(long id) {
		this.problemId = id;
	}
	public String getProblemName() {
		return problemName;
	}
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public int getUsersTried() {
		return usersTried;
	}
	public void setUsersTried(int usersTried) {
		this.usersTried = usersTried;
	}
	public int getUsersSolved() {
		return usersSolved;
	}
	public void setUsersSolved(int usersSolved) {
		this.usersSolved = usersSolved;
	}
	public int getSubmissionsTried() {
		return submissionsTried;
	}
	public void setSubmissionsTried(int submissionsTried) {
		this.submissionsTried = submissionsTried;
	}
	public int getSubmissionsSolved() {
		return submissionsSolved;
	}
	public void setSubmissionsSolved(int submissionsSolved) {
		this.submissionsSolved = submissionsSolved;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	public long getTimelimit() {
		return timelimit;
	}
	public void setTimelimit(long timelimit) {
		this.timelimit = timelimit;
	}
	
	
	
	
}
