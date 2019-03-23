package com.example.friojspring.Controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.assertj.core.groups.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.friojspring.Domain.UserDTO;
import com.example.friojspring.Exceptions.UnauthorizedException;
import com.example.friojspring.Helpers.Converters;
import com.example.friojspring.Helpers.ZipHelper;
import com.example.friojspring.Model.TestCase;
import com.example.friojspring.Model.Problem;
import com.example.friojspring.Model.User;
import com.example.friojspring.NonEntities.UserProblem;
import com.example.friojspring.Security.JwtUser;
import com.example.friojspring.Services.ProblemService;
import com.example.friojspring.Services.UserService;

@RestController
@RequestMapping(value="/problems")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/allProblems")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Problem>> getAllProblems(){
		List<Problem> problems = problemService.findAll();
		return new ResponseEntity<List<Problem>>(problems,HttpStatus.OK);
	}
	
	@PostMapping(value="/addProblem" )
	public ResponseEntity<String> addProblem(/*@RequestPart("pdf") MultipartFile pdf,*/
			@RequestPart("input") MultipartFile input,/*@RequestPart("output") MultipartFile output,*/
			@RequestParam("name") String name/*, @RequestParam("timelimit") String timelimit,
			@RequestParam("hidden") boolean hidden*/) throws IOException{
	
        try {
        	File zippedFile = Converters.convert(input);
        	String fileName=zippedFile.getName().substring(0, zippedFile.getName().length()-4);
        	String unzippedFileLocation="temp/"+fileName;
        	ZipHelper.extractFolder(zippedFile.getAbsolutePath(),unzippedFileLocation);
        	
        	boolean correctFormat = ZipHelper.checkFormatOfProblemDirectory(unzippedFileLocation);
        	File unzippedProblemFile = new File(unzippedFileLocation);
        	
        	File inputFolder = new File(unzippedProblemFile.getAbsolutePath()+"/input");
        	File outputFolder = new File(unzippedProblemFile.getAbsolutePath()+"/output");
        	

        	Problem p = new Problem(name, 100);
        	
        	String[] inputFileNames = inputFolder.list();
        	String[] outputFileNames = outputFolder.list();
        	
        	ArrayList<TestCase> testCases = new ArrayList<>();
        	for (int i = 0; i < outputFileNames.length; i++) {
				testCases.add(new TestCase(inputFileNames[i],outputFileNames[i], p));
			}

        	p.setTestCases(testCases);
        	p=problemService.save(p);
        	
        	zippedFile.delete(); //delete zipFile
        	String newDirectory = "Problems/"+p.getId();
        	unzippedProblemFile.renameTo(new File(newDirectory)); //move from temp to problems
        	unzippedProblemFile=new File(newDirectory);
        	//renaming pdfFile
        	String pdfFileName=".pdf";
        	for (File file : unzippedProblemFile.listFiles()) {
				if(file.getName().endsWith(".pdf")) {
					pdfFileName=file.getName();
				}
			}
        	File pdfFile = new File(newDirectory+"/"+pdfFileName);
        	pdfFile.renameTo(new File(newDirectory+"/"+p.getId()+".pdf"));
        	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/pdf/problem{problemId}.pdf" , method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPdfFile(@PathVariable("problemId") long problemId) throws IOException {

		
		//byte[] res=problemService.getPdf(problemId);
		byte[] res = Files.readAllBytes(new File("Problems/"+problemId+"/"+problemId+".pdf").toPath());
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("charset", "utf-8");
	    responseHeaders.setContentType(MediaType.APPLICATION_PDF);
	    responseHeaders.setContentLength(res.length);
	    responseHeaders.set("Content-disposition", "inline; filename=problem"+problemId+".pdf");

	    return new ResponseEntity<byte[]>(res, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/input/problem{problemId}.in")
	public ResponseEntity<byte[]> getInputFile(@PathVariable("problemId") long problemId) throws FileNotFoundException {

		byte[] res=problemService.getInput(problemId);
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("charset", "utf-8");
	    responseHeaders.setContentType(MediaType.TEXT_PLAIN);
	    responseHeaders.setContentLength(res.length);
	    responseHeaders.set("Content-disposition", "inline; filename=problem"+problemId+".in");

	    return new ResponseEntity<byte[]>(res, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/output/problem{problemId}.out")
	public ResponseEntity<byte[]> getOutputFile(@PathVariable("problemId") long problemId) throws FileNotFoundException {

	    byte[] res=problemService.getOutput(problemId);
	    
 	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("charset", "utf-8");
	    responseHeaders.setContentType(MediaType.TEXT_PLAIN);
	    responseHeaders.setContentLength(res.length);
	    responseHeaders.set("Content-disposition", "inline; filename=problem"+problemId+".out");

	    return new ResponseEntity<byte[]>(res, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value="/userProblems/problem{problemId}")
	public ResponseEntity<UserProblem> getUserProblem(@PathVariable("problemId") long problemId){
		
		//String username=SecurityContextHolder.getContext().getAuthentication().getName();
		String username="Matheuss";
		UserProblem up=problemService.getUserProblem(username, problemId);
		
		return new ResponseEntity<UserProblem>(up,HttpStatus.OK);
	}
	
	@GetMapping(value="/userProblems")
	public ResponseEntity<List<UserProblem>> getVisibleUserProblems(/*@RequestBody PageRequest page*/){
		
		//String username=SecurityContextHolder.getContext().getAuthentication().getName();
		User user = new User();
		user.setUsername("Matheuss");
		Pageable pgb = new PageRequest(0,10);
		List<UserProblem> problems=problemService.getVisibleUserProblems(user, pgb);
		//problems=problemService.getAllProblemRowsForUser(user);
		
		return new ResponseEntity<List<UserProblem>>(problems,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	@GetMapping(value="/allUsersWhoSolvedThisProblem")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsersWhoSolvedThisProblem(/*@RequestBody Problem problem*/){
		Problem p = new Problem();
		p.setId(1l);
		List<User> users = userService.getAllUsersWhoSolvedThisProblem(p);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/visibleProblems")
	public ResponseEntity<List<Problem>> getVisibleProblems(){
		List<Problem> problems = problemService.findVisibleProblems(new PageRequest(0, 5)).getContent();
		return new ResponseEntity<List<Problem>>(problems,HttpStatus.OK);
	}
	
	
	/*@GetMapping(value="/rows")
	public ResponseEntity<List<UserProblem>> getVisibleProblemRowsForUser(){
		User u = userService.getUserByUsername("Matheuss");
		System.out.println(u.getEmail()+" "+u.getFirstName());
		List<UserProblem> problems = problemService.getVisibleProblemRowsForUser(u,new PageRequest(0, 5));
		return new ResponseEntity<List<UserProblem>>(problems,HttpStatus.OK);
	}*/
	
}
