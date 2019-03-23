package com.example.friojspring.Repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.User;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long>{

	List<Submission> findByLanguage(Language language);
	List<Submission> findByResult(Result result);

    Page<Submission> findByLanguage(Language language, Pageable pageable);
    Page<Submission> findByResult(Result result, Pageable pageable);
    
    List<Submission> findByUser(User user);
    List<Submission> findByUserAndResult(User user, Result result);
    
    @Query("select new com.example.friojspring.Domain.SubmissionDTO("
    		+ "s.id, s.problem.id, s.language.code,s.result.code, s.submissionTime, s.code.code) from Submission s "
    		+ "WHERE s.user.username=:username")
    List<SubmissionDTO> getAllSubmissionsForUser(@Param("username")String username);
    
    @Query("select new com.example.friojspring.Domain.SubmissionDTO("
    		+ "s.id, s.problem.id, s.language.code,s.result.code, s.submissionTime, s.code.code, s.user.username) from Submission s")
    List<SubmissionDTO> getAllSubmissions();
    
    @Query("select new com.example.friojspring.Domain.SubmissionDTO("
    		+ "s.id, s.problem.id, s.result.id, s.user.username) from Submission s")
    List<SubmissionDTO> getAllSubmissionsForRanklist();
    
    @Query("select new com.example.friojspring.Domain.SubmissionDTO("
    		+ "s.id, s.problem.id, s.language.code,s.result.code, s.submissionTime, s.code.code, s.user.username) from Submission s "
    		+ "WHERE s.id=:submissionId")
    SubmissionDTO getSubmissionDTO(@Param("submissionId")long submissionId);
    
    @Query("select new com.example.friojspring.Domain.SubmissionDTO("
    		+ "s.id, s.user.id, s.problem.id,s.result.id, s.submissionTime) from Submission s "
    		+ "WHERE s.submissionTime>=:from AND s.submissionTime<=:till AND s.user.id IN :userIDs AND s.problem.id IN :problemIDs")   
	List<SubmissionDTO> getSubmissions(@Param("userIDs") List<Long> userIDs,@Param("problemIDs") List<Long> problemsIDs, 
									   @Param("from") Date from, @Param("till") Date till);
    
   


}
