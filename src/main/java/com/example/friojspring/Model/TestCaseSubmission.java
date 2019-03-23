package com.example.friojspring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Test_case_submission")
public class TestCaseSubmission {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TestCase testCase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Submission submission;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
	private Result result;
	
	private long runningTime;

	
	
	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public Submission getSubmission() {
		return submission;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}

	/*public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}*/

	public long getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}
	
	
	
	
}
