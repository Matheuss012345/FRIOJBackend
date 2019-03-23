package com.example.friojspring.NonEntities;

import java.util.Date;

public class ContestTableCell {
	
	private int numberOfSubmissions;
	private Date firstAccepted;
	private int numberOfSubmissionsBeforeAccepted;
	
	public ContestTableCell(int numberOfSubmissions, Date firstAccepted, int numberOfSubmissionsBeforeAccepted) {
		super();
		this.numberOfSubmissions = numberOfSubmissions;
		this.firstAccepted = firstAccepted;
		this.numberOfSubmissionsBeforeAccepted = numberOfSubmissionsBeforeAccepted;
	}

	public int getNumberOfSubmissions() {
		return numberOfSubmissions;
	}
	
	public void setNumberOfSubmissions(int numberOfSubmissions) {
		this.numberOfSubmissions = numberOfSubmissions;
	}
	
	public Date getFirstAccepted() {
		return firstAccepted;
	}
	
	public void setFirstAccepted(Date firstAccepted) {
		this.firstAccepted = firstAccepted;
	}
	
	public int getNumberOfSubmissionsBeforeAccepted() {
		return numberOfSubmissionsBeforeAccepted;
	}
	
	public void setNumberOfSubmissionsBeforeAccepted(int numberOfSubmissionsBeforeAccepted) {
		this.numberOfSubmissionsBeforeAccepted = numberOfSubmissionsBeforeAccepted;
	}
	
	public String toString() {
		return "   Cell [No. of submissions : "+numberOfSubmissions+" , First acc. " +firstAccepted+"]";
	}
	
	
	
}
