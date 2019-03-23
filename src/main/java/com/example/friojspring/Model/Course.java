package com.example.friojspring.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Course")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="course_user",
		joinColumns = @JoinColumn(name="course_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
		
	)	
	private Set<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL)
	private Set<Exercise> exercises;

	public Course(String name) {
		super();
		this.name = name;
	}
	
	public Course() {
		
	}

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	
	
	
	
	
}
