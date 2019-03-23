package com.example.friojspring.NonEntities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ContestTableRow {
	String name;
	List<ContestTableCell> cells;
	double score;
	
	ContestTable table;

	public ContestTableRow(String name,ContestTable table) {
		super();
		this.name = name;
		this.cells = new ArrayList<>();
		this.table=table;
	}

    public static Comparator<ContestTableRow> scoreComparator = new Comparator<ContestTableRow>() {

        @Override
        public int compare(ContestTableRow o1, ContestTableRow o2) {
        	long score1 =o1.getScore();
        	long score2 =o2.getScore();
        	if(score1>score2) return -1;
        	if(score1<score2) return 1;
        	
        	long totalTime1 = o1.getTotalTimeInMinutes();
        	long totalTime2 = o2.getTotalTimeInMinutes();
        	if(totalTime1>totalTime2) return 1;
        	if(totalTime1<totalTime2) return -1;
        	
        	return 0;
        }
    };


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContestTableCell> getCells() {
		return cells;
	}
	
	public void setCells(List<ContestTableCell> cells) {
		this.cells = cells;
	}
	
	public void addContestCell(ContestTableCell cell) {
		cells.add(cell);
	}
	
	public long getScore() {
		long score=0;
		/*for (ContestCell cell : cells) {
			if(cell.getFirstAccepted()!=null) score++;
		}*/
		for (int i = 0; i < cells.size(); i++) {
			if(cells.get(i).getFirstAccepted()!=null) {
				score+=table.getPoints().get(i);
			}
		}
		//System.out.println("sizes"+cells.size()+" ,"+points.size());
		return score;
		 
	}
	
	public String toString() {
		String res="";
		res+= "  Row ["+name+"]\n";
		for (ContestTableCell contestCell : cells) {
			res+=contestCell+"\n";
		}
		
		return res;
	}

	public long getTotalTimeInMinutes() {
		long totalTime=0;
		Date start = table.getStartingTime();
		for (ContestTableCell contestCell : cells) {
			if(contestCell.getFirstAccepted()!=null) {
				System.out.println(name+" "+contestCell.getFirstAccepted()+"S "+start);
				totalTime+=(contestCell.getFirstAccepted().getTime()-start.getTime())/(1000*60);
			}
		}
		
		return totalTime;
	}



	
	
	
	
}
