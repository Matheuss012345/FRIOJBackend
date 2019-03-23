package com.example.friojspring.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.User;


public interface CourseService {

	List<Course> getAllCourses();
	Course insertCourse(Course course);
	void deleteCourse(long id);
	Course getCourse(long id);
	void removeUserFromCourse(long courseId,long userId);
	void addUserToCourse(long courseId,long userId);
	Set<User> getCourseUsers(long courseId);
	Set<User> getUsersNotInCourse(long courseId);
}
