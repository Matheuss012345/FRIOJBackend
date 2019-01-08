package com.example.friojspring.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAlias;

import com.example.friojspring.Helpers.*;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="username", unique=true,nullable=false)
	@Pattern(regexp = Patterns.usernamePattern)
	private String username;
	
	@JsonAlias("firstname")
	@Column(name="first_name")
	@Pattern(regexp = Patterns.namePattern)
	private String firstName;
	
	@JsonAlias("lastname")
	@Column(name="last_name")
	@Pattern(regexp = Patterns.namePattern)
	private String lastName;
	
	@Column(name="email")
	@Email
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="role")
	private String role;

	
	public User(String username, String firstName, String lastName, String email, String password, boolean enabled,
			String phoneNumber, Date createdDate) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.phoneNumber = phoneNumber;
		this.createdDate = createdDate;
	}
	
	public User(String username, String firstName, String lastName, String email, String password, boolean enabled,
			Date createdDate) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.createdDate = createdDate;
	}

	public User() {		
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", phoneNumber="
				+ phoneNumber + ", createdDate=" + createdDate + ", role=" + role + "]";
	}
	
	

	
	
	
}
