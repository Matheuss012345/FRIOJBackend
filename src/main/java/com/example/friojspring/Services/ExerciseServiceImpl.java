package com.example.friojspring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.Exercise;
import com.example.friojspring.Model.ExerciseProblem;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.SimulatedContest;
import com.example.friojspring.Model.SimulatedSubmission;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.ContestTable;
import com.example.friojspring.NonEntities.ExerciseRow;
import com.example.friojspring.Repositories.ExerciseRepository;

@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {

	@Autowired
	CourseService courseService;
	
	@Autowired
	ProblemService problemService;
	
	@Autowired
	SubmissionService submissionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	
	
	
	@Override
	public Set<Exercise> getAllCourseExercises(long courseId) {
		Course course = courseService.getCourse(courseId);
		System.out.println(course.getId()+" "+course.getName() );
		
		Set<Exercise> res = course.getExercises();
		return res;
	}

	@Override
	public Exercise addExercise(Exercise exercise, long courseId) {
		Course course = courseService.getCourse(courseId);
		exercise.setCourse(course);
		return exerciseRepository.save(exercise);
	}

	@Override
	public void deleteExercise(long exerciseId) {
		exerciseRepository.deleteById(exerciseId);
		
	}

	@Override
	public Set<ExerciseProblem> getExerciseProblems(long exerciseId) {
		return exerciseRepository.getOne(exerciseId).getExerciseProblems();
	}

	@Override
	public Exercise getExercise(long exerciseId) {
		return exerciseRepository.getOne(exerciseId);
	}

	@Override
	public void addExerciseProblem(long exercisId, long problemId, long points) {
		Problem problem = problemService.findById(problemId);
		Exercise exercise = exerciseRepository.getOne(exercisId);
		ExerciseProblem exerciseProblem= new ExerciseProblem(exercise, problem, points);
		exercise.addExerciseProblem(exerciseProblem);
		
		exerciseRepository.save(exercise);
		
	}

	/*@Override
	public ContestTable getContestTable(long exerciseId) {
		Exercise exercise = exerciseRepository.getOne(exerciseId);
		System.out.println(exercise==null?"exercise null":"exercise good");
		List<User> users = new ArrayList<>();
		List<Problem> problems = new ArrayList<>();
		List<Long> points = new ArrayList<>();
		//fill user IDs
		ArrayList<Long> userIDs = new ArrayList<>();
		for (User user : exercise.getCourse().getUsers()) {
			userIDs.add(user.getId());
			users.add(user);
			System.out.println(user);
		}
		System.out.println("User ids good");
		//fill problem IDs
		ArrayList<Long> problemIDs = new ArrayList<>();
		for (ExerciseProblem problem  : exercise.getExerciseProblems()) {
			problemIDs.add(problem.getProblem().getId());
			problems.add(problem.getProblem());
			points.add(problem.getPoints());
			System.out.println(problem.getProblem().getName());
		}
		System.out.println("problem ids good");
		
		List<SubmissionDTO> submissions = submissionService.getSubmissions(userIDs, problemIDs, exercise.getStartingTime(), exercise.getEndingTime());
		
		System.out.println("submissionsx");
		for (SubmissionDTO submissionDTO : submissions) {
			System.out.println(submissionDTO);
		}
		//return new ContestTable(users, problems,points, submissions);
		return null;
	}

	@Override
	public ContestTable getContestTable(long exerciseId, Date till) {
		Exercise exercise = exerciseRepository.getOne(exerciseId);
		List<User> users = new ArrayList<>();
		List<Problem> problems = new ArrayList<>();
		List<Long> points = new ArrayList<>();
		//fill user IDs
		ArrayList<Long> userIDs = new ArrayList<>();
		for (User user : exercise.getCourse().getUsers()) {
			userIDs.add(user.getId());
			users.add(user);
			
		}
		
		//fill problem IDs
		ArrayList<Long> problemIDs = new ArrayList<>();
		for (ExerciseProblem problem  : exercise.getExerciseProblems()) {
			problemIDs.add(problem.getProblem().getId());
			problems.add(problem.getProblem());
			points.add(problem.getPoints());
		}
		
		List<SubmissionDTO> submissions = submissionService.getSubmissions(userIDs, problemIDs, exercise.getStartingTime(), till);

		
		//return new ContestTable(users, problems,points, submissions);
		return null;
	}*/

	@Override
	public void cloneExercise(long exerciseTo, long exerciseFrom) {
		Set<ExerciseProblem> exerciseProblemsFrom = exerciseRepository.getOne(exerciseFrom).getExerciseProblems();
		for (ExerciseProblem exerciseProblem : exerciseProblemsFrom) {
			System.out.println(exerciseProblem);
		}
		
		Exercise e  = exerciseRepository.getOne(exerciseTo);
		
		
		for (ExerciseProblem exerciseProblem : exerciseProblemsFrom) {
			e.getExerciseProblems().add(new ExerciseProblem(e,exerciseProblem.getProblem(),exerciseProblem.getPoints()));
		}
		
		exerciseRepository.save(e);
		
		
	}

	@Override
	public List<ExerciseRow> getAllUserExercises(String username) {
		User currentUser = userService.getUserByUsername(username);
		
		List<ExerciseRow> userExercises = new ArrayList<>();
		for (Course course :currentUser.getCourses()) {
			for (Exercise exercise : course.getExercises()) {
				userExercises.add(new  ExerciseRow(exercise, course));
			}
		}
		//sort by starting time
		userExercises.sort(ExerciseRow.startingTimeComparator);
		
		return userExercises;
	}

	@Override
	public ContestTable getContestTable(long exerciseId) {
		Exercise exercise = exerciseRepository.getOne(exerciseId);
		
		//NAMES
		List<String> names = new ArrayList<>();
		for (User user : exercise.getCourse().getUsers()) {
			names.add(user.getUsername());
		}
				
		//PROBLEMS
		List<Problem> problems = new ArrayList<>();
		//POINTS
		List<Long> points = new ArrayList<>();
				
		for (ExerciseProblem exerciseProblem : exercise.getExerciseProblems()) {
			problems.add(exerciseProblem.getProblem());
			points.add(exerciseProblem.getPoints());
		}

		
		//SUBMISSIONS
		List<SubmissionDTO> submissionDTOs = new ArrayList<>();
		for (Submission s : exercise.getSubmissions()) {
			SubmissionDTO subDTO = new SubmissionDTO(s.getId(), s.getProblem().getId(),
									s.getResult().getId(), s.getUser().getUsername(), s.getSubmissionTime());
			
			submissionDTOs.add(subDTO);
		}
		
		ContestTable table = new ContestTable(names, problems, points, submissionDTOs,exercise.getStartingTime(),exercise.getEndingTime());
		return table;
		
	}

	@Override
	public ContestTable getContestTable(long exerciseId, Date till) {
		// TODO Auto-generated method stub
		return null;
	}


}
