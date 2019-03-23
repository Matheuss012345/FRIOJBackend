package com.example.friojspring.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.User;
import com.example.friojspring.Repositories.CourseRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}


	@Override
	public Course insertCourse(Course course) {
		return courseRepository.save(course);
	}


	@Override
	public void deleteCourse(long id) {
		courseRepository.deleteById(id);	
		
	}


	@Override
	public Course getCourse(long id) {
		return courseRepository.getOne(id);
	}


	@Override
	public void removeUserFromCourse(long courseId,long userId) {
		courseRepository.removeUserFromCourse(courseId, userId);		
	}


	@Override
	public void addUserToCourse(long courseId, long userId) {
		courseRepository.addUserToCourse(courseId, userId);
		
	}


	@Override
	public Set<User> getCourseUsers(long courseId) {
		return courseRepository.getOne(courseId).getUsers();
	}


	@Override
	public Set<User> getUsersNotInCourse(long courseId) {
		Set<User> courseUsers = getCourseUsers(courseId);
		List<User> allUsers = userService.findAll();
		Set<User> result = new HashSet<User>();
		for (User user : allUsers) {
			if(!courseUsers.contains(user)) {
				result.add(user);
			}
		}
		
		return result;
		
	}

}
