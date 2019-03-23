package com.example.friojspring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.SimulatedContest;
import com.example.friojspring.Model.SimulatedSubmission;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.ContestRow;
import com.example.friojspring.NonEntities.ContestTable;
import com.example.friojspring.NonEntities.MyContests;
import com.example.friojspring.Repositories.ContestRepository;
import com.example.friojspring.Repositories.SimulatedContestRepository;

@Service
@Transactional
public class ContestServiceImpl implements ContestService{

	@Autowired
	ContestRepository contestRepository;
	
	@Autowired
	SimulatedContestRepository simulatedContestRepository;
	
	@Override
	public ContestTable getContestTable(long contestId) {
		Contest contest = contestRepository.getOne(contestId);
		
		//NAMES
		List<String> names = new ArrayList<>();
		for (Team team : contest.getTeams()) {
			names.add(team.getName());
		}
				
		//PROBLEMS
		List<Problem> problems = contest.getProblems();
		
		//POINTS
		List<Long> points = new ArrayList<>(problems.size());
		for (int i = 0; i < problems.size(); i++) {
			points.add(1l);
		}
		
		//SUBMISSIONS
		List<SubmissionDTO> submissionDTOs = new ArrayList<>();
		for (Submission s : contest.getSubmissions()) {
			SubmissionDTO subDTO = new SubmissionDTO(s.getId(), s.getProblem().getId(),
									s.getResult().getId(), s.getTeam().getName(), s.getSubmissionTime());
			
			submissionDTOs.add(subDTO);
		}
		
		
		//SIMULATED CONTEST
		SimulatedContest simulatedContest = contest.getSimulatedContest();
		
		//ak bol pouzity aj sim. contest
		if(simulatedContest!=null) {
			
			Date start = contest.getStartingTime();
			
			for (SimulatedSubmission simSub : simulatedContest.getSimulatedSubmissions()) {
				
				Date subTime = new Date(start.getTime()+simSub.getSubmissionTimeInMs());
				
				SubmissionDTO subDTO = new SubmissionDTO(0, simSub.getProblem().getId(),simSub.getResult(), simSub.getTeam(), subTime);

				submissionDTOs.add(subDTO);
				if(!names.contains(simSub.getTeam())) names.add(simSub.getTeam());
			}
		}

		
		ContestTable table = new ContestTable(names, problems, points, submissionDTOs,contest.getStartingTime(),contest.getEndingTime());
		return table;
		
	}

	@Override
	public MyContests getMyContests(User user) {
		Set<Team> myTeams = user.getTeams();
		List<ContestRow> contestRows = new ArrayList<>();
		for (Team team : myTeams) {
			for (Contest contest : team.getContests()) {
				contestRows.add(new ContestRow(contest, team));
			}
		}
		
		return new MyContests(contestRows);
	}

	@Override
	public Contest getContest(Long contestId) {
		return contestRepository.getOne(contestId);
	}

}
