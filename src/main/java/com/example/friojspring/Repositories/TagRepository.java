package com.example.friojspring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long>{

	Tag findByName(String tagname);
	
	//list of problem tags
	@Query(value="SELECT * FROM tag t JOIN problem_tag pt ON t.id=pt.tag_id JOIN problem p ON pt.problem_id=p.id "
			+ "WHERE p.id=:problemID" , nativeQuery=true)
	List<Tag> findProblemTags(@Param("problemID") long problemID);

}