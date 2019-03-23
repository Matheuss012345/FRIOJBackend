package com.example.friojspring.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Submission")
public class Submission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Problem problem;
	
	@ManyToOne
	private User user;
	
	@OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
	private SubmissionCode code;
	
	@ManyToOne
	private Language language;
	
	@ManyToOne
	private Result result;
	
	@Column(name="submission_time")
	private Date submissionTime;
	
	@OneToMany(mappedBy = "submission",fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<TestCaseSubmission> testCaseSubmissions;
	
	//ak bol submit na conteste
	@JsonIgnore
	@ManyToOne
	private Contest contest;
	
	//ak bol submit na exercise
	@JsonIgnore
	@ManyToOne
	private Exercise exercise;
	
	//ak bol submit od timu
	@JsonIgnore
	@ManyToOne
	private Team team;

	
	public Submission(Problem problem, User user, String code, Language language, Result result, Date submissionTime) {
		super();
		this.problem = problem;
		this.user = user;
		this.code = new SubmissionCode(code, this);
		this.language = language;
		this.result = result;
		this.submissionTime = submissionTime;
	}
	
	public Submission(Problem problem, User user, String code, Language language, Result result, Date submissionTime , Contest contest, Team team) {
		super();
		this.problem = problem;
		this.user = user;
		this.code = new SubmissionCode(code, this);
		this.language = language;
		this.result = result;
		this.submissionTime = submissionTime;
		this.team = team;
		this.contest = contest;
	}
	
	public Submission(Problem problem, User user, String code, Language language, Result result, Date submissionTime , Exercise exercise) {
		super();
		this.problem = problem;
		this.user = user;
		this.code = new SubmissionCode(code, this);
		this.language = language;
		this.result = result;
		this.submissionTime = submissionTime;
		this.exercise = exercise;
	}
	
	public Submission() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Submission [id=" + id + ", problem=" + problem.getId() + ", user=" + user.getFirstName() + ", code=" + code + ", language="
				+ language.getCode() + ", result=" + result.getCode() + ", submissionTime=" + submissionTime.toString() + "]";
	}

	public SubmissionCode getCode() {
		return code;
	}

	public void setCode(SubmissionCode code) {
		this.code = code;
	}

	public List<TestCaseSubmission> getTestCaseSubmissions() {
		return testCaseSubmissions;
	}

	public void setTestCaseSubmissions(List<TestCaseSubmission> testCaseSubmissions) {
		this.testCaseSubmissions = testCaseSubmissions;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	
	
	
	
}
