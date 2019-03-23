package com.example.friojspring.Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExerciseProblemPrimaryKey implements Serializable{
	
	@Column(name="exercise_id")
	private long exerciseId;
	
	@Column(name="problem_id")
	private long problemId;

	public ExerciseProblemPrimaryKey(long exerciseId, long problemId) {
		super();
		this.exerciseId = exerciseId;
		this.problemId = problemId;
	}
	
	public ExerciseProblemPrimaryKey() {
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseProblemPrimaryKey)) return false;
        
        if( ((ExerciseProblemPrimaryKey)o).getExerciseId()!=exerciseId) return false;
        if( ((ExerciseProblemPrimaryKey)o).getProblemId()!=problemId) return false;
        return true;
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, problemId);
    }
	
	
	

	public long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public long getProblemId() {
		return problemId;
	}

	public void setProblemId(long problemId) {
		this.problemId = problemId;
	}
	
	
}
