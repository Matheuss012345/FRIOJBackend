package com.example.friojspring.Services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.friojspring.Model.Exercise;
import com.example.friojspring.Model.ExerciseProblem;
import com.example.friojspring.NonEntities.ContestTable;
import com.example.friojspring.NonEntities.ExerciseRow;

public interface ExerciseService {
	Set<Exercise> getAllCourseExercises(long courseId);
	Exercise addExercise(Exercise exercise, long courseId);
	void deleteExercise(long exerciseId);
	Set<ExerciseProblem> getExerciseProblems(long exerciseId);
	Exercise getExercise(long exerciseId);
	void addExerciseProblem(long exercisId,long problemId, long points);
	ContestTable getContestTable(long exerciseId);
	ContestTable getContestTable(long exerciseId, Date till);
	void cloneExercise(long exerciseTo, long exerciseFrom);
	
	List<ExerciseRow> getAllUserExercises(String username);
}
