package com.example.friojspring.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
