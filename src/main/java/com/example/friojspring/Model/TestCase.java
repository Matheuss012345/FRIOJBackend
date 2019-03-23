package com.example.friojspring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Test_Case")
public class TestCase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String inputFileName;
	
	@Column(nullable=false)
	private String outputFileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Problem problem;

	
	public TestCase(String inputFileName, String outputFileName, Problem problem) {
		super();
		this.inputFileName=inputFileName;
		this.outputFileName=outputFileName;
		this.problem = problem;
	}

	public TestCase() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	
	
	
	
	
	
}
