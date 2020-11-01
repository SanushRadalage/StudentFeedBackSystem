package com.feedback.model;

import java.io.Serializable;

public class Analytics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4271194907664394749L;
	int lecturerID;
	int questionID;
	int answer1Count;
	int answer2Count;
	int answer3Count;
	int answer4Count;
	
	
	
	public Analytics() {
		super();
	}



	public Analytics(int lecturerID, int questionID, int answer1Count, int answer2Count, int answer3Count,
			int answer4Count) {
		super();
		this.lecturerID = lecturerID;
		this.questionID = questionID;
		this.answer1Count = answer1Count;
		this.answer2Count = answer2Count;
		this.answer3Count = answer3Count;
		this.answer4Count = answer4Count;
	}



	public int getLecturerID() {
		return lecturerID;
	}



	public void setLecturerID(int lecturerID) {
		this.lecturerID = lecturerID;
	}



	public int getQuestionID() {
		return questionID;
	}



	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}



	public int getAnswer1Count() {
		return answer1Count;
	}



	public void setAnswer1Count(int answer1Count) {
		this.answer1Count = answer1Count;
	}



	public int getAnswer2Count() {
		return answer2Count;
	}



	public void setAnswer2Count(int answer2Count) {
		this.answer2Count = answer2Count;
	}



	public int getAnswer3Count() {
		return answer3Count;
	}



	public void setAnswer3Count(int answer3Count) {
		this.answer3Count = answer3Count;
	}



	public int getAnswer4Count() {
		return answer4Count;
	}



	public void setAnswer4Count(int answer4Count) {
		this.answer4Count = answer4Count;
	}



	@Override
	public String toString() {
		return "Analytics [lecturerID=" + lecturerID + ", questionID=" + questionID + ", answer1Count=" + answer1Count
				+ ", answer2Count=" + answer2Count + ", answer3Count=" + answer3Count + ", answer4Count=" + answer4Count
				+ "]";
	}
	
	
	
	
	
}
