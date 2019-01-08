package com.example.friojspring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Result;
import com.example.friojspring.Repositories.ResultRepository;

@Service
@Transactional
public class ResultServiceImpl implements ResultService{

	@Autowired
	ResultRepository resultRepository;
	
	@Override
	public Result findByCode(String code) {
		return resultRepository.findByCode(code);
	}

	@Override
	public Result findById(Long id) {
		return resultRepository.getOne(id);
	}
	
	@Override
	public Result save(Result result) {
		return resultRepository.save(result);
	}

}
