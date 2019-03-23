package com.example.friojspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.ExerciseProblem;
import com.example.friojspring.Model.ExerciseProblemPrimaryKey;

@Repository
public interface ExerciseProblemRepository extends JpaRepository<ExerciseProblem,ExerciseProblemPrimaryKey>{

}
