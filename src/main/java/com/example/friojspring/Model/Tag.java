package com.example.friojspring.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Tag")

public class Tag {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@Column(name="name",unique=true)
	private String name;
	
	@JsonIgnore
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
	private Set<Problem> problems;

	public Tag(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Tag() {
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Problem> getProblems() {
		return problems;
	}

	public void setProblems(Set<Problem> problems) {
		this.problems = problems;
	}
	
	
	
	
	
	
}
