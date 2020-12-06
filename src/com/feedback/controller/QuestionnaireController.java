package com.feedback.controller;

import java.rmi.Naming;
import java.util.List;

import com.feedback.interfaces.RemoteInterface;
import com.feedback.model.Admin;
import com.feedback.model.Analytics;
import com.feedback.model.Lecturer;
import com.feedback.model.Questionnaire;

/**
 * Centralized remote methods by retrieving remote object reference. This class
 * is accessible for all GUI's.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class QuestionnaireController {

	RemoteInterface remoteInterface;

	public QuestionnaireController() {
		try {
			remoteInterface = (RemoteInterface) Naming.lookup("rmi://localhost/QuestionService");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Lecturer> lecturerList() throws Exception {
		return remoteInterface.lecturerList();
	}

	public List<Questionnaire> getQuestions() throws Exception {
		return remoteInterface.getQuestions();
	}

	public void updateAnalytics(Analytics analytics) throws Exception {
		remoteInterface.setAnalytics(analytics);
	}

	public List<Analytics> getAnalytics(int lecturerId) throws Exception {
		return remoteInterface.getAnalytics(lecturerId);
	}

	public String cartConfiguration(String chartType, List<Analytics> analyticsList, int questionId) throws Exception {
		return remoteInterface.chartConfigure(chartType, analyticsList, questionId);
	}

	public Boolean signIn(Admin admin) throws Exception {
		return remoteInterface.signInData(admin);
	}

	public void sendEmailWithAttachments(String toAddress) throws Exception {
		remoteInterface.sendEmailWithAttachments(toAddress);
	}
}
