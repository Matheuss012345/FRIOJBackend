package com.example.friojspring.Services;

import com.example.friojspring.Model.Contest;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.ContestTable;
import com.example.friojspring.NonEntities.MyContests;

public interface ContestService {

	ContestTable getContestTable(long contestId);
	MyContests getMyContests(User user);
	Contest getContest(Long contestId);
}
