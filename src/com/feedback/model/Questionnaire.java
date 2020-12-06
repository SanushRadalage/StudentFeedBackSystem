package com.feedback.model;

import java.io.Serializable;

/**
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class Questionnaire implements Serializable {
	
	private static final long serialVersionUID = 1898696433964023773L;
	
	private int questionId;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	
	public Questionnaire(int questionId, String question, String answer1, String answer2, String answer3,
			String answer4) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	


	
}
