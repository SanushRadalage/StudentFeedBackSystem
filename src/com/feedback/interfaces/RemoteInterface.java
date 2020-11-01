package com.feedback.interfaces;

import java.rmi.Remote;
import java.util.List;
import com.feedback.model.Analytics;
import com.feedback.model.LecturerInformation;
import com.feedback.model.Questionnaire;

public interface RemoteInterface extends Remote {
	
	   public List<Questionnaire> getQuestions() throws Exception;
	   public List<LecturerInformation> lecturerList() throws Exception;
	   public Boolean signInData(String username,String password) throws Exception;
	   public void setAnalytics(Analytics analytics) throws Exception;
	   public List<Analytics> getAnalytics(int lecId) throws Exception;
	   public String chartConfigure() throws Exception;
	   
}
