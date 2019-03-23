package com.example.friojspring.NonEntities;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RanklistRow {
	String username;
	Set<Long> problemsSolved;
	int submissions;
	
	public RanklistRow(String username) {
		this.username=username;
		this.problemsSolved=new HashSet<>();
		this.submissions=0;
	}
	
	public static Comparator<RanklistRow> scoreComparator = new Comparator<RanklistRow>() {

        @Override
        public int compare(RanklistRow o1, RanklistRow o2) {
        	long score1 =o1.getProblemsSolved().size();
        	long score2 =o2.getProblemsSolved().size();
        	if(score1>score2) return -1;
        	if(score1<score2) return 1;
        	
        	return 0;
        }
    };
	
	public String getUsername() {
		return username;
	}
	
	public Set<Long> getProblemsSolved() {
		return problemsSolved;
	}
	
	public int getSubmissions() {
		return submissions;
	}
	
	public void addSubmission() {
		submissions++;
	}
	
	public void addProblemSolved(long id) {
		problemsSolved.add(id);
	}
	
	
	
}
