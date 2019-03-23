package com.example.friojspring.Model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Exercise_problem")
public class ExerciseProblem {

	@EmbeddedId
	private ExerciseProblemPrimaryKey exerciseProblemPrimaryKey;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("exercise_id")
	@JoinColumn(name="exercise_id")
	private Exercise exercise;
	
	//@JsonIgnore
	@ManyToOne
	@MapsId("problem_id")
	@JoinColumn(name="problem_id")
	private Problem problem;
	
	private long points;
	
	
	public ExerciseProblem(Exercise exercise, Problem problem, long points) {
		super();
		this.points = points;
		this.exerciseProblemPrimaryKey = new ExerciseProblemPrimaryKey(exercise.getId(),problem.getId());
	}
	
	public ExerciseProblem() {
		
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}
	
	public String toString() {
		return "ExerciseProblem:[ ExerciseID: "+this.exercise.getId()+" , problemId: "+problem.getId()+" , points: "+points+" ]";
	}
	
}
