package com.example.friojspring.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Model.Exercise;
import com.example.friojspring.Model.ExerciseProblem;
import com.example.friojspring.NonEntities.ContestTable;
import com.example.friojspring.NonEntities.ExerciseRow;
import com.example.friojspring.Services.ExerciseService;

@RestController
@RequestMapping(value="/exercises")
public class ExerciseController {
	
	@Autowired
	private ExerciseService exerciseService;
	
	@GetMapping(value="/exercise/{exerciseId}")
	public ResponseEntity<Exercise> getExercise(@PathVariable long exerciseId){
		Exercise exercise = exerciseService.getExercise(exerciseId);
		return new ResponseEntity<Exercise>(exercise,HttpStatus.OK);
	}
	
	@GetMapping(value="/allExercises/course/{courseId}")
	public ResponseEntity<Set<Exercise>> getAllCourseExercises(@PathVariable long courseId){
		Set<Exercise> courseExercises = exerciseService.getAllCourseExercises(courseId);
		return new ResponseEntity<Set<Exercise>>(courseExercises,HttpStatus.OK);
	}
	
	@PostMapping(value="addExercise/course/{courseId}")
	public ResponseEntity<Void> addExercise(@PathVariable long courseId, @RequestBody Exercise exercise){
		exerciseService.addExercise(exercise,courseId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value="deleteExercise/{exerciseId}")
	public ResponseEntity<Void> addExercise(@PathVariable long exerciseId){
		exerciseService.deleteExercise(exerciseId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/exercise/{exerciseId}/problems")
	public ResponseEntity<Set<ExerciseProblem>> getExerciseProblems(@PathVariable long exerciseId){
		Set<ExerciseProblem> exerciseProblems = exerciseService.getExerciseProblems(exerciseId);
		return new ResponseEntity<Set<ExerciseProblem>>(exerciseProblems,HttpStatus.OK);
	}
	
	@PostMapping(value="/exercise/{exerciseId}/addExerciseProblem/{problemId}")
	public ResponseEntity<Void> addExerciseProblem(@PathVariable long exerciseId,@PathVariable long problemId,@RequestBody ExerciseProblem problem){
		exerciseService.addExerciseProblem(exerciseId, problemId, problem.getPoints());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/exercise/{exerciseId}/contestTable")
	public ResponseEntity<ContestTable> getContestTable(@PathVariable long exerciseId){
		ContestTable contestTable = exerciseService.getContestTable(exerciseId);
		
		System.out.println(contestTable);
		
		return new ResponseEntity<ContestTable>(contestTable,HttpStatus.OK);
	}
	
	@PostMapping(value="/exercise/{exerciseTo}/cloneExercise/from/{exerciseFrom}")
	public ResponseEntity<Void> cloneExercise(@PathVariable long exerciseTo,@PathVariable long exerciseFrom){
		exerciseService.cloneExercise(exerciseTo,exerciseFrom);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping(value="/myExercises")
	public ResponseEntity<List<ExerciseRow>> getMyExercises(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<ExerciseRow> exercises = exerciseService.getAllUserExercises(username);
		
		return new ResponseEntity<List<ExerciseRow>>(exercises,HttpStatus.OK);
	}
	
	
	
	
}
