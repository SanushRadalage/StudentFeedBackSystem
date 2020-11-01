package com.feedback.controller;

import java.rmi.Naming;
import java.util.List;
import com.feedback.interfaces.RemoteInterface;
import com.feedback.model.Analytics;
import com.feedback.model.LecturerInformation;
import com.feedback.model.Questionnaire;


public class QuestionnaireController {

	RemoteInterface remoteInterface;

	public QuestionnaireController() {
		try {
			remoteInterface = (RemoteInterface) Naming.lookup("rmi://localhost/QuestionService");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<LecturerInformation> lecturerList() throws Exception{
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
	
	public String cartConfiguration() throws Exception {
		return remoteInterface.chartConfigure();
	}
	
}
