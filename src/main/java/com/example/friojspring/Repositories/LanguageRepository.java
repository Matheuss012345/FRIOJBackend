package com.example.friojspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Submission;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long>{
	Language findByCode(String code);
}
