package com.example.friojspring.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;

@Entity
@Table(name="Problem")
public class Problem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private long timelimit;
	
	@Column(nullable=false)
	private boolean hidden;
	
	@JsonIgnore
	@Lob
	@Column(nullable=false)
	private byte[] pdf;
	
	@JsonIgnore
	@Lob
	@Column(nullable=false)
	private byte[] input;
	
	@JsonIgnore
	@Lob
	@Column(nullable=false)
	private byte[] output;
	
	@JsonIgnoreProperties("problems")
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "problem_tag", joinColumns = 
    	@JoinColumn(name = "problem_id", referencedColumnName = "id"),
			 inverseJoinColumns = 
		 		@JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private Set<Tag> tags/*= new HashSet<>()*/;
	
	
	
	public Problem(String name, long timelimit, boolean hidden, byte[] pdf, byte[] input, byte[] output) {
		super();
		this.name = name;
		this.timelimit = timelimit;
		this.hidden = hidden;
		this.pdf = pdf;
		this.input = input;
		this.output = output;
	}




	public Problem(Set<Tag> tags) {
		this.tags=tags;
	}
	
	public Problem(long id) {
		this.id=id;
	}

	public Problem() {
		super();
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

	public long getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(long timelimit) {
		this.timelimit = timelimit;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public byte[] getInput() {
		return input;
	}

	public void setInput(byte[] input) {
		this.input = input;
	}

	public byte[] getOutput() {
		return output;
	}

	public void setOutput(byte[] output) {
		this.output = output;
	}
	
	public MultipartFile getMultipartPdf() {
		String name="problem"+id+".pdf";
		String contentType = "application/pdf";
		MultipartFile result = new MockMultipartFile(name, name, contentType, pdf);
		
		return result;
	}
	
	
	
}
