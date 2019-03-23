package com.example.friojspring.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="team")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIgnoreProperties(value={ "teamMembers" }, allowSetters=true)
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String name;
	
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    private User teamCaptain;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_team", joinColumns = 
    	@JoinColumn(name = "team_id", referencedColumnName = "id"),
			 inverseJoinColumns = 
		 		@JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> teamMembers= new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="team",cascade=CascadeType.ALL)
	private List<Submission> submissions;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "teams")
	private List<Contest> contests;


	public Team(String name) {
		super();
		this.name = name;
	}
	
	public Team(String name,User teamCaptain) {
		super();
		this.name = name;
		this.teamCaptain = teamCaptain;
	}
	
	public Team() {
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


	public Set<User> getTeamMembers() {
		return teamMembers ;
	}


	
	public void removeTeamMember(User user) {
		teamMembers.remove(user);
	}
	
	
	

	public User getTeamCaptain() {
		return teamCaptain;
	}

	public void setTeamCaptain(User teamCaptain) {
		this.teamCaptain = teamCaptain;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeamMembers(Set<User> teamMembers) {
		this.teamMembers = teamMembers;
	}
	

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	public List<Contest> getContests() {
		return contests;
	}

	public void setContests(List<Contest> contests) {
		this.contests = contests;
	}
	
	public void addSubmission(Submission s) {
		submissions.add(s);
	}

	@Override
	public String toString() {
		String res= "Team [id=" + id + ", name=" + name + "]";
		
		for (User user : teamMembers) {
			res+="\n"+user;
		}
		return res;
	}
	
	
	
	
	
}
