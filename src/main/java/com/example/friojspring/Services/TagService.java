package com.example.friojspring.Services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Tag;

public interface TagService {
	Tag findByName(String name);
	
	Tag save(Tag tag);
	
	List<Tag> findProblemTags(Problem problem);
}
