package com.example.friojspring.Services;

import java.util.List;

import com.example.friojspring.Model.Language;

public interface LanguageService {
	Language findByCode(String code);
	Language findById(Long id);
	
	Language save(Language language);
	List<Language> findAll();
}
