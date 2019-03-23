package com.example.friojspring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Submission_code")
public class SubmissionCode {
	
	@Id
	private Long id;
	
	@Lob
	@Column(nullable=false)
	private String code;
	
	
	@OneToOne
    @JoinColumn(name="id")
    @MapsId
	private Submission submission;
	
		
	public SubmissionCode(String code, Submission submission) {
		super();
		this.code = code;
		this.submission = submission;
	}
	
	public SubmissionCode() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
