package com.example.friojspring.NonEntities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyContests {

	List<ContestRow> past = new ArrayList<>();
	List<ContestRow> ongoing = new ArrayList<>();
	List<ContestRow> upcoming = new ArrayList<>();
	
	public MyContests(List<ContestRow> contestRows) {
		Date now = new Date();
		for (ContestRow contestRow : contestRows) {
			if(contestRow.getStartingTime().after(now)) upcoming.add(contestRow);
			else if(contestRow.getEndingTime().before(now)) past.add(contestRow);
			else ongoing.add(contestRow);
		}
	}

	public List<ContestRow> getPast() {
		return past;
	}

	public void setPast(List<ContestRow> past) {
		this.past = past;
	}

	public List<ContestRow> getOngoing() {
		return ongoing;
	}

	public void setOngoing(List<ContestRow> ongoing) {
		this.ongoing = ongoing;
	}

	public List<ContestRow> getUpcoming() {
		return upcoming;
	}

	public void setUpcoming(List<ContestRow> upcoming) {
		this.upcoming = upcoming;
	}
	
	
}
