package com.example.friojspring.Services;

import com.example.friojspring.Model.Result;

public interface ResultService {
	Result findByCode(String code);
	Result findById(Long id);
	
	Result save(Result result);
}
