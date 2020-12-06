package com.feedback.interfaces;

import java.rmi.Remote;
import java.util.List;

import com.feedback.model.Admin;
import com.feedback.model.Analytics;
import com.feedback.model.Lecturer;
import com.feedback.model.Questionnaire;

/**
 * RMI interface which consist with abstract methods to exchange the data in
 * between server and the client.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */

public interface RemoteInterface extends Remote {

	/**
	 * Read question list from the database
	 * 
	 * @return Questionnaire list
	 * @throws Exception
	 */
	public List<Questionnaire> getQuestions() throws Exception;

	/**
	 * Read lecturer list from the database
	 * 
	 * @return Lecturer list
	 * @throws Exception
	 */
	public List<Lecturer> lecturerList() throws Exception;

	/**
	 * check the admin data that client application provided with admin data in the
	 * database
	 * 
	 * @param admin
	 * @return the provided admin data is correct or not
	 * @throws Exception
	 */
	public Boolean signInData(Admin admin) throws Exception;

	/**
	 * Update the selected lecturer's answer count for each question
	 * 
	 * @param analytics
	 * @throws Exception
	 */
	public void setAnalytics(Analytics analytics) throws Exception;

	/**
	 * Read the answer count for each question of selected lecturer
	 * 
	 * @param lecId
	 * @return Analytics list
	 * @throws Exception
	 */
	public List<Analytics> getAnalytics(int lecId) throws Exception;

	/**
	 * quickchart.io API integration. This method can generate bar and pie charts according to the admin's choice.
	 * 
	 * @param chartType
	 * @param analyticsList
	 * @param questionId
	 * @return chart url
	 * @throws Exception
	 */
	public String chartConfigure(String chartType, List<Analytics> analyticsList, int questionId) throws Exception;

	/**
	 * Send email with attachments
	 * 
	 * @param toAddress
	 * @throws Exception
	 */
	public void sendEmailWithAttachments(String toAddress) throws Exception;

}