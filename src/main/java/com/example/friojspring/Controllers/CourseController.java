package com.example.friojspring.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.User;
import com.example.friojspring.Services.CourseService;

@RestController
@RequestMapping(value="/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping(value="/allCourses")
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> courses = courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);
	}
	
	@PostMapping(value="/addCourse")
	public ResponseEntity<Course> addCourse(@RequestBody Course course){
		Course courseSaved = courseService.insertCourse(new Course(course.getName()));
		return new ResponseEntity<Course>(courseSaved,HttpStatus.OK);
	}
	
	
	@PostMapping(value="/deleteCourse")
	public ResponseEntity<Void> deleteCourse(@RequestBody Course course){
		courseService.deleteCourse(course.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/course/{courseId}/users")
	public ResponseEntity<Set<User>> getAllCourseUsers(@PathVariable Long courseId){
		Set<User> courseUsers = courseService.getCourseUsers(courseId);
		return new ResponseEntity<Set<User>>(courseUsers,HttpStatus.OK);
	}
	
	@GetMapping(value="/course/{courseId}/usersNotInCourse")
	public ResponseEntity<Set<User>> getAllUsersNoptInCourse(@PathVariable Long courseId){
		Set<User> usersNotInCourse = courseService.getUsersNotInCourse(courseId); 
		return new ResponseEntity<Set<User>>(usersNotInCourse,HttpStatus.OK);
	}
	
	// "/course/"+courseId+"/deleteUser",user);
	@PostMapping(value="/course/{courseId}/deleteUser")
	public ResponseEntity<Void> deleteUserFromCourse(@RequestBody User user,@PathVariable long courseId){
		courseService.removeUserFromCourse(courseId,user.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value="/course/{courseId}/addUser")
	public ResponseEntity<Void> addUserToCourse(@RequestBody User user,@PathVariable long courseId){
		System.out.println(user);
		courseService.addUserToCourse(courseId, user.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/course/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable Long courseId){
		Course course = courseService.getCourse(courseId);;
		return new ResponseEntity<Course>(course,HttpStatus.OK);
	}
}
