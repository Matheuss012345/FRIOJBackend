package com.example.friojspring.Model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.friojspring.NonEntities.ContestTableRow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Exercise")
public class Exercise {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Date startingTime;
	
	private Date endingTime;
	
	@JsonIgnore
	@ManyToOne
	private Course course;
	
	@JsonIgnore
	@OneToMany(mappedBy="exercise",cascade= {CascadeType.ALL})
	private Set<ExerciseProblem> exerciseProblems;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY , mappedBy="exercise",cascade=CascadeType.ALL)
	private List<Submission> submissions;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<ExerciseProblem> getExerciseProblems() {
		return exerciseProblems;
	}

	public void setExerciseProblems(Set<ExerciseProblem> exerciseProblems) {
		this.exerciseProblems = exerciseProblems;
	}
	
	public void addExerciseProblem(ExerciseProblem problem) {
		this.exerciseProblems.add(problem);
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}
	

	
	
	
	
}
