package com.example.friojspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.SimulatedContest;

@Repository
public interface SimulatedContestRepository extends JpaRepository<SimulatedContest,Long> {

}
