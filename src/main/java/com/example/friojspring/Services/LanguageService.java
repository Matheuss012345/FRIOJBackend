package com.example.friojspring.Services;

import com.example.friojspring.Model.Language;

public interface LanguageService {
	Language findByCode(String code);
	Language findById(Long id);
	
	Language save(Language language);
}
