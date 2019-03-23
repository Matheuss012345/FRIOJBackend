package com.example.friojspring.Repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.Exercise;
import com.example.friojspring.Model.ExerciseProblem;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.Submission;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long>{

}
