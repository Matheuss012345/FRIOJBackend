package com.example.friojspring.Controllers;

	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Services.LanguageService;
import com.example.friojspring.Services.ProblemService;
import com.example.friojspring.Services.UserService;

@RestController
@RequestMapping(value="/languages")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@GetMapping(value="/allLanguages")
	public ResponseEntity<List<Language>> getAllLanguages(){
		List<Language> languages = languageService.findAll();
		return new ResponseEntity<List<Language>>(languages,HttpStatus.OK);
	}
	
	
	
}
