package com.example.friojspring.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.UserProblem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long>{
	List<Problem> findByName(String name);
	List<Problem> findByTagsName(String name);
	Page<Problem> findAll(Pageable pageable);
	Optional<Problem> findById(Long id);
	
	//----------------PDF/INPUT/OUTPUT
	@Query(value="SELECT pdf FROM problem p WHERE p.id=:id" , nativeQuery=true)
	byte[] findPdfById(@Param("id")long id);
	
	@Query(value="SELECT input FROM problem p WHERE p.id=:id" , nativeQuery=true)
	byte[] findInputById(@Param("id")long id);
	
	@Query(value="SELECT output FROM problem p WHERE p.id=:id" , nativeQuery=true)
	byte[] findOutputById(@Param("id")long id);
	
	
	
	
	
	@Query(value="SELECT * FROM problem p WHERE p.hidden=FALSE" , nativeQuery=true)
	Page<Problem> findVisibleProblems(Pageable pageable);
	
	@Query(value="SELECT DISTINCT problem_id FROM submission s JOIN user u on u.id=s.user_id "
		    	+ "JOIN result r on r.id=s.result_id "
			    + "WHERE u.username=:username AND r.code='AC'" , nativeQuery=true)
	Set<Long> findIdsOfAllSolvedProblemsOfUser(@Param("username")String username);
	
	@Query(value="SELECT COUNT(*) FROM submission s JOIN user u on u.id=s.user_id "
	    	+ "JOIN result r on r.id=s.result_id "
		    + "WHERE u.username=:username AND r.code='AC' AND s.problem_id=:problemId" , nativeQuery=true)
	int getNumerOfAcceptedSubmissionOfThisProblemForThisUser(@Param("problemId")long problemId,@Param("username")String username);
	
	
	
	//STATISTICS OF A PROBLEM
	@Query(value="SELECT COUNT(DISTINCT s.user_id) FROM submission s JOIN result r on s.result_id=r.id "
			+ "WHERE r.code='AC' and s.problem_id=:problemID" , nativeQuery=true)
	int getNumberOfUsersWhoSolvedThisProblem(@Param("problemID")long problemID);
	
	@Query(value="SELECT COUNT(DISTINCT s.user_id) FROM submission s JOIN result r on s.result_id=r.id "
			+ "WHERE s.problem_id=:problemID" , nativeQuery=true)
	int getNumberOfUsersWhoTriedThisProblem(@Param("problemID")long problemID);
	
	@Query(value="SELECT COUNT(s.id) FROM submission s WHERE s.problem_id=:problemID" , nativeQuery=true)
	int getNumberOfAllSubmissionsOfThisProblem(@Param("problemID")long problemID);
	
	@Query(value="SELECT COUNT(s.id) FROM submission s join result r on s.result_id=r.id "
			  + "WHERE problem_id=:problemID and r.code='AC'" , nativeQuery=true)
	int getNumberOfAllAcceptedSubmissionsOfThisProblem(@Param("problemID")long problemID);
	
	
	///---------------------getproblem--------------------
	
	@Query(value="SELECT id, name, timelimit FROM problem p where p.id=:problemID" , nativeQuery=true)
	Problem getDTOProblemById(@Param("problemID")long problemID);
	
}
