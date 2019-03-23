package com.example.friojspring.NonEntities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.friojspring.Constants.Constants;
import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ContestTable {
	
	@JsonIgnore
	private List<String> names;
	
	private List<Problem> problems;
	
	private List<Long> points;
	
	private List<ContestTableRow> contestRows;
	
	@JsonIgnore
	private List<SubmissionDTO> submissions;
	
	private Date startingTime;
	private Date endingTime;

	
	
	public ContestTable(List<String> names, List<Problem> problems,List<Long> points, List<SubmissionDTO> submissions, Date startingTime, Date endingTime) {
		super();
		this.names = names;
		this.problems = problems;
		this.points=points;
		this.submissions=submissions;
		this.contestRows=new ArrayList<>();
		this.startingTime=startingTime;
		this.endingTime=endingTime;
		
		createTable();
		sortContestRows();
	}
	
	private void createTable() {
		Date now = new Date();
		
		Date firstAccepted;
		int numberOfSubmissions;
		int solvedProblems;
		
		for (String name : names) {
			ContestTableRow row = new ContestTableRow(name,this);
			solvedProblems=0;
			//System.out.println(user.getUsername());
			
			for (Problem problem : problems) {
				numberOfSubmissions=0;
				firstAccepted=null;
				//System.out.println(problem.getName());
				
				for (SubmissionDTO submission : submissions) {
					//submission belongs to this user and this problem
					
					if(submission.getProblemID()==problem.getId() 
							&& submission.getUsername().equals(name)
							&& submission.getSubmissionTime().before(now)) {
						//if submission is accepted
						if(isAccepted(submission)) {
							//System.out.println("sub2x"+submission);
							if(firstAccepted==null) {
								firstAccepted=submission.getSubmissionTime();
								solvedProblems++;
								//System.out.println("sub3x"+submission);
							}
							else {
								//System.out.println("sub4x"+submission);
								if(firstAccepted.compareTo(submission.getSubmissionTime())<1) {
									firstAccepted=submission.getSubmissionTime();
									//System.out.println("sub5x"+submission);
								}
							}
						}
						numberOfSubmissions++;
						
					}
				}
				ContestTableCell cell = new ContestTableCell(numberOfSubmissions, firstAccepted, -1);		
				row.addContestCell(cell);
			}
			contestRows.add(row);
			
		}
	}
	
	private boolean isAccepted(SubmissionDTO submission) {
		return submission.getResultId()==Constants.ACCEPTED_ID;
	}
	
	private void sortContestRows() {
		contestRows.sort(ContestTableRow.scoreComparator);
	}



	public List<Problem> getProblems() {
		return problems;
	}

	public List<ContestTableRow> getContestRows() {
		return contestRows;
	}

	public List<SubmissionDTO> getSubmissions() {
		return submissions;
	}
	
	public String toString() {
		String res="";
		res+="Table\n";
		res+=" Users: \n";
		for (String name : names) {
			res+="  "+name+"\n";
		}
		res+=" Problems: \n";
		for (Problem p : problems) {
			res+="  "+p.getId()+" , "+p.getName()+"\n";
		}
		
		res+=" Rows: \n";
		for (ContestTableRow row : contestRows) {
			res+="  "+row+"\n";
		}
		
		return res;
	}

	public List<Long> getPoints() {
		return points;
	}
	
	public void setPoints(List<Long> points) {
		this.points=points;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}
	
	
	
}
