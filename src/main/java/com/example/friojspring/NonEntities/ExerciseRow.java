package com.example.friojspring.NonEntities;

import java.util.Comparator;
import java.util.Date;

import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.Exercise;
import com.example.friojspring.Model.Team;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExerciseRow {
	
	private String courseName;
	private String exerciseName;
	private Long exerciseId;	

	@JsonFormat(pattern="yyyy, d.MMMM HH:mm:ss")
	private Date startingTime;
	
	@JsonFormat(pattern="yyyy, d.MMMM HH:mm:ss")
	private Date endingTime;
	
	public ExerciseRow(Exercise exercise, Course course) {

		this.startingTime=exercise.getStartingTime();
		this.endingTime=exercise.getEndingTime();
		this.courseName = course.getName();
		this.exerciseName = exercise.getName();
		this.exerciseId = exercise.getId();

	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
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
	
	public static Comparator<ExerciseRow> startingTimeComparator = new Comparator<ExerciseRow>() {
        @Override
        public int compare(ExerciseRow o1, ExerciseRow o2) {
        	return o1.getStartingTime().compareTo(o2.getEndingTime());
        }
    };
}
