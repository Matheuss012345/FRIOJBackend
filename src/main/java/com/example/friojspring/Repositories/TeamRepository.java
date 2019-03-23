package com.example.friojspring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Team;
import com.example.friojspring.Model.User;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long>{
	
	@Query(value="SELECT * FROM team t JOIN user_team ut ON t.id=ut.team_id WHERE ut.user_id= :userId", nativeQuery=true)
	List<Team> findAllUserTeams(@Param("userId") Long userId);

	
	
	@Modifying
	@Query(value="DELETE FROM team WHERE id= :teamId", nativeQuery=true)
	void deleteTeamById(@Param("teamId")long teamId);
	
	@Modifying
	@Query(value="DELETE FROM user_team WHERE team_id= :teamId", nativeQuery=true)
	void deleteAllTeamMembers(@Param("teamId")long teamId);
	

}
