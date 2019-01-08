package com.example.friojspring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Tag;
import com.example.friojspring.Repositories.ResultRepository;
import com.example.friojspring.Repositories.TagRepository;

@Service
@Transactional
public class TagServiceImpl implements TagService{

	@Autowired
	TagRepository tagRepository;

	@Override
	public Tag findByName(String tagname) {
		return tagRepository.findByName(tagname);
	}
	
	public Tag findById(long id) {
		return tagRepository.getOne(id);
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public List<Tag> findProblemTags(Problem problem) {
		return tagRepository.findProblemTags(problem.getId());
	}
	


}
