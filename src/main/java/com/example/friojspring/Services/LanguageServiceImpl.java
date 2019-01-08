package com.example.friojspring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Repositories.LanguageRepository;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	LanguageRepository languageRepository;

	@Override
	public Language findByCode(String code) {
		return languageRepository.findByCode(code);
	}

	@Override
	public Language findById(Long id) {
		return languageRepository.getOne(id);
	}
	
	@Override
	public Language save(Language language) {
		return languageRepository.save(language);
	}

}
