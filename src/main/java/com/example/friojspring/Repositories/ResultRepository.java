package com.example.friojspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long>{
	Result findByCode(String code);
}
