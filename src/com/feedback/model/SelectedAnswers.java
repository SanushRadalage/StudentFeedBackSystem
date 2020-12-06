package com.feedback.model;

/**
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class SelectedAnswers {

	int questionId;
	int selectedAnswerId;
	public SelectedAnswers(int questionId, int selectedAnswerId) {
		super();
		this.questionId = questionId;
		this.selectedAnswerId = selectedAnswerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getSelectedAnswerId() {
		return selectedAnswerId;
	}
	public void setSelectedAnswerId(int selectedAnswerId) {
		this.selectedAnswerId = selectedAnswerId;
	}
	
	
}
