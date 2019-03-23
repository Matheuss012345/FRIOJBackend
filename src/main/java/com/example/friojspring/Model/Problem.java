package com.example.friojspring.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;

@Entity
@Table(name="Problem")
public class Problem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private long timelimit;
	
	@Column(nullable=false)
	private boolean hidden;
	
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "problem_tag", joinColumns = 
    	@JoinColumn(name = "problem_id", referencedColumnName = "id"),
			 inverseJoinColumns = 
		 		@JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private Set<Tag> tags;
	
	@JsonIgnore
	@OneToMany(mappedBy = "problem",fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<TestCase> testCases;
	
	@JsonIgnore
	@OneToMany(mappedBy="problem",fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<ExerciseProblem> exerciseProblems;
	
	public Problem(String name, long timelimit, boolean hidden) {
		super();
		this.name = name;
		this.timelimit = timelimit;
		this.hidden = hidden;
	}
	
	public Problem(String name, long timelimit) {
		super();
		this.name = name;
		this.timelimit = timelimit;
		this.hidden = false;
	}




	public Problem(Set<Tag> tags) {
		this.tags=tags;
	}
	
	public Problem(long id) {
		this.id=id;
	}

	public Problem() {
		super();
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

	public long getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(long timelimit) {
		this.timelimit = timelimit;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}
	
	
	
	
	
	
	
	
	
}
