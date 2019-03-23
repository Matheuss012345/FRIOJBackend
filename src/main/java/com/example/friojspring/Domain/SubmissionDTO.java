package com.example.friojspring.Domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;

public class SubmissionDTO {
	
	private long userId;
	private String username;
	private String code;
	private long problemID;
	private long languageID;
	private Date submissionTime;
	private long runningTime;
	private long submissionID;
	private String language;
	private long resultId;
	private String result;
	
	private Long teamId;
	private Long contestId;
	private Long exerciseId;
	
	
	
	public SubmissionDTO(String code, long problemID, long languageID, Date submissionTime) {
		super();
		this.code = code;
		this.problemID = problemID;
		this.languageID = languageID;
		this.submissionTime = submissionTime;
	}
	
	public SubmissionDTO(long id,long problemId, String language, String result, Date submissionTime,String code) {
		this.problemID=problemId;
		this.submissionID=id;
		this.language=language;
		this.result=result;
		this.submissionTime=submissionTime;
		this.code=code;
	}
	
	public SubmissionDTO(long id,long problemId, String language, String result, Date submissionTime,String code,String username) {
		this.problemID=problemId;
		this.submissionID=id;
		this.language=language;
		this.result=result;
		this.submissionTime=submissionTime;
		this.code=code;
		this.username=username;
	}
	
	public SubmissionDTO(long id, long userId, long problemId, long resultId, Date submissionTime) {
		this.submissionID=id;
		this.userId=userId;
		this.problemID=problemId;
		this.resultId=resultId;
		this.submissionTime=submissionTime;
	}
	
	public SubmissionDTO(long id, long problemId, long resultId, String username) {
		this.submissionID=id;
		this.problemID=problemId;
		this.resultId=resultId;
		this.username=username;
		
	}
	
	public SubmissionDTO(long id, long problemId, long resultId, String username,Date submissionTime) {
		this.submissionID=id;
		this.problemID=problemId;
		this.resultId=resultId;
		this.username=username;
		this.submissionTime=submissionTime;
		
	}
	
	public SubmissionDTO() {
		
	}
		
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public long getProblemID() {
		return problemID;
	}
	
	public void setProblemID(long problemID) {
		this.problemID = problemID;
	}
	
	public long getLanguageID() {
		return languageID;
	}
	
	public void setLanguageID(long languageID) {
		this.languageID = languageID;
	}
	
	public Date getSubmissionTime() {
		return submissionTime;
	}
	
	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public long getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getSubmissionID() {
		return submissionID;
	}

	public void setSubmissionID(long submissionId) {
		this.submissionID = submissionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getResultId() {
		return resultId;
	}

	public void setResultId(long resultId) {
		this.resultId = resultId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getContestId() {
		return contestId;
	}

	public void setContestId(Long contestId) {
		this.contestId = contestId;
	}
	
	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}
	
	
	
	@Override
	public String toString() {
		return "SubmissionDTO [userId=" + userId + ", username=" + username + ", problemID=" + problemID
				+ ", languageID=" + languageID + ", submissionTime=" + submissionTime==null?"null":submissionTime  
				+		", submissionID=" + submissionID
				+ ", resultId=" + resultId + "]";
	}
	
	
	
	
	
	
	
}
