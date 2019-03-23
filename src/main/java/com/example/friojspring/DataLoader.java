package com.example.friojspring;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.Result;
import com.example.friojspring.Model.Submission;
import com.example.friojspring.Model.Tag;
import com.example.friojspring.Model.User;
import com.example.friojspring.Repositories.ProblemRepository;
import com.example.friojspring.Services.LanguageService;
import com.example.friojspring.Services.ProblemService;
import com.example.friojspring.Services.ResultService;
import com.example.friojspring.Services.SubmissionService;
import com.example.friojspring.Services.TagService;
import com.example.friojspring.Services.UserService;
 
@Component
public class DataLoader implements CommandLineRunner {
 
	@Autowired
	LanguageService languageService;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	ProblemService problemService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SubmissionService submissionService;
    
	@Autowired
	ProblemRepository problemRepository;
 
    @Override
    public void run(String... strings) throws Exception {
       /* //create languages
    	Language java = new Language("JAVA");
    	Language cpp = new Language("C++");
    	Language c = new Language("C");
    	
    	languageService.save(java);
    	languageService.save(cpp);
    	languageService.save(c);
    	
    	//create results
    	
    	Result WA = new Result("WA", "Wrong answer");
    	Result NE = new Result("NE", "Not evaluated");
    	Result TLE = new Result("TLE", "Time limit exceeded");
    	Result CE = new Result("CE", "Compilation error");
    	Result RTE = new Result("RTE", "Run time exception");
    	Result AC = new Result("AC", "Accepted");
    	
    	resultService.save(WA);
    	resultService.save(NE);
    	resultService.save(TLE);
    	resultService.save(CE);
    	resultService.save(RTE);
    	resultService.save(AC);
    	
    	//create tags
    	Tag graphTheoryTag = new Tag("Graph theory", "Graph theory");
    	Tag dpTag = new Tag("Dynamic programming", "Dynamic programming");
    	Tag mathTag = new Tag("Mathematics", "Mathematics");
    	Tag simulationTag = new Tag("Simulation", "Simulation");
    	Tag combinatoricsTag = new Tag("Combinatorics", "Combinatorics");
    	Tag geometryTag = new Tag("Geometry", "Geometry");
    	Tag binarySearchTag = new Tag("Binary search", "Binary search");
    	
    	tagService.save(graphTheoryTag);
    	tagService.save(dpTag);
    	tagService.save(mathTag);
    	tagService.save(simulationTag);
    	tagService.save(combinatoricsTag);
    	tagService.save(geometryTag);
    	tagService.save(binarySearchTag);
    	
    	//users
    	User user1 = new User("Matheuss", "Matt", "Xy", "xy@azet.sk", "password", true, "123456", new Date());
    	User user2 = new User("Alex1", "Alex", "King", "king@azet.sk", "password", true, "0123", new Date());
    	User user3 = new User("Jurij", "Jurij", "Queens", "jq@gmail.sk", "password", true, "123456789", new Date());
    	User user4 = new User("Johnny", "John", "Q", "JQ@movies.edu", "password", true, "2222", new Date());
    	User user5 = new User("Alain123", "Alain", "Delone", "delone@delone.fr", "password", true, "0123789", new Date());
    	User user6 = new User("Elizabeth4", "Queen", "Elizabeth", "eliska@queen.gb", "password", true, "12345", new Date());
    	
    	userService.save(user1);
    	userService.save(user2);
    	userService.save(user3);
    	userService.save(user4);
    	userService.save(user5);
    	userService.save(user6);
    	
    	//problems
    	
    	Problem p1 = new Problem("Problem 1", 2000, true, new byte[] {(byte)'A'}, new byte[] {(byte)'A'}, new byte[] {(byte)'A'});
    	Problem p2 = new Problem("Problem 2", 3000, false, new byte[] {(byte)'A'}, new byte[] {(byte)'A'}, new byte[] {(byte)'A'});
    	Problem p3 = new Problem("Problem 3", 4000, true, new byte[] {(byte)'A'}, new byte[] {(byte)'A'}, new byte[] {(byte)'A'});
    	Problem p4 = new Problem("Problem 4", 2000, false, new byte[] {(byte)'A'}, new byte[] {(byte)'A'}, new byte[] {(byte)'A'});
    	Problem p5 = new Problem("Problem 5", 2000, true, new byte[] {(byte)'A'}, new byte[] {(byte)'A'}, new byte[] {(byte)'A'});
    	Problem p6 = new Problem("Problem 6", 1000, false, new byte[] {(byte)'A'}, new byte[] {(byte)'A'}, new byte[] {(byte)'A'});
    	
    	p1=problemService.save(p1);
    	p2=problemService.save(p2);
    	p3=problemService.save(p3);
    	p4=problemService.save(p4);
    	p5=problemService.save(p5);
    	p6=problemService.save(p6);
    	
    	//adding tags to problem
    	*/
    	/*User u = userService.getUserByUsername("Matheuss");
    	Problem p = problemService.findById(2);
    	Tag dptTag = tagService.findByName("Mathematicsu");
    	Language l = languageService.findByCode("C");
    	Result res = resultService.findByCode("AC");
    	p.addTag(dptTag);
    	problemService.save(p);
    	
    	
    	
    	//submissions
    	Submission s1 = new Submission(p, u, "printout(done);", l, res, new Date());
    	s1=submissionService.save(s1);
    	
    	//Result AC = new Result("AC", "Accepted");
    	//resultService.save(AC);*/
    	
    	Problem pr = new Problem("A", 10l, true);
    	problemService.save(pr);
 
    }
}