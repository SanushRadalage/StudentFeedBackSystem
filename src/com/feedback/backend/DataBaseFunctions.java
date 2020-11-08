package com.feedback.backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.feedback.interfaces.RemoteInterface;
import com.feedback.model.Analytics;
import com.feedback.model.LecturerInformation;
import com.feedback.model.Questionnaire;

public class DataBaseFunctions extends UnicastRemoteObject implements RemoteInterface {
	
	private static final long serialVersionUID = -9088567564909054283L;
	Connection connection = null;
	Statement statement = null;
	
	public DataBaseFunctions() throws Exception {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/feedbackdb", "root", "");
			System.out.println("Connected database successfully...");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Questionnaire> getQuestions() throws Exception {
		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
		
		String sql = "SELECT * FROM questions";

		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			Questionnaire questionnaire = new Questionnaire(resultSet.getInt("QuestionID"), resultSet.getString("Question"), resultSet.getString("Answer1"), resultSet.getString("Answer2"), resultSet.getString("Answer3"),resultSet.getString("Answer4"));
			questionnaireList.add(questionnaire);
		}
				
		resultSet.close();
		
		return questionnaireList;
	}

	@Override
	public List<LecturerInformation> lecturerList() throws Exception {

		ArrayList<LecturerInformation> lecturersList = new ArrayList<>();
		
		String sql = "SELECT * FROM lecturer";

		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			LecturerInformation lecturerInformation = new LecturerInformation(resultSet.getInt("LecturerID"), resultSet.getString("LecturerName"));
			lecturersList.add(lecturerInformation);
		}
				
		resultSet.close();
		
		return lecturersList;
	}


	@Override
	public Boolean signInData(String username, String password) throws Exception {
		ResultSet resultSet = null;

		statement = connection.createStatement();
		String sql = "SELECT * FROM admin WHERE UserName ='" + username + "' AND Password ='" + password + "'";
		
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultSet.next()) {
			resultSet.close();
			return true;

		} else {
			resultSet.close();
			return false;
		}
	}

	@Override
	public void setAnalytics(Analytics analytics) throws Exception {
		statement = connection.createStatement();

		String sql = "UPDATE analytics SET Answer1Count = '" + analytics.getAnswer1Count() + "', Answer2Count = " + " '" + analytics.getAnswer2Count() + "', Answer3Count = " + " '" + analytics.getAnswer3Count() + "', Answer4Count = " + " '"
				+ analytics.getAnswer4Count() + "' WHERE LecturerID = '" + analytics.getLecturerID() + "'AND QuestionID = '" + analytics.getQuestionID() + "'";
		
		statement.executeUpdate(sql);
		
	}

	@Override
	public List<Analytics> getAnalytics(int lecId) throws Exception {

		ArrayList<Analytics> analyticsList = new ArrayList<>();
		
		String sql = "SELECT * FROM analytics WHERE LecturerID = '" + lecId + "'";
		
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			Analytics analytics = new Analytics(resultSet.getInt("LecturerID"), resultSet.getInt("QuestionID"), resultSet.getInt("Answer1Count"), resultSet.getInt("Answer2Count"), resultSet.getInt("Answer3Count"), resultSet.getInt("Answer4Count"));
			analyticsList.add(analytics);
		}
		
		return analyticsList;
	}

	@Override
	public String chartConfigure(String chartType, List<Analytics> analyticsList) throws Exception {
		HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
		
		Analytics analytics1 = new Analytics(analyticsList.get(0).getLecturerID(), analyticsList.get(0).getQuestionID(), analyticsList.get(0).getAnswer1Count(), analyticsList.get(0).getAnswer2Count(), analyticsList.get(0).getAnswer3Count(), analyticsList.get(0).getAnswer4Count());
		Analytics analytics2 = new Analytics(analyticsList.get(1).getLecturerID(), analyticsList.get(1).getQuestionID(), analyticsList.get(1).getAnswer1Count(), analyticsList.get(1).getAnswer2Count(), analyticsList.get(1).getAnswer3Count(), analyticsList.get(1).getAnswer4Count());
		Analytics analytics3 = new Analytics(analyticsList.get(2).getLecturerID(), analyticsList.get(2).getQuestionID(), analyticsList.get(2).getAnswer1Count(), analyticsList.get(2).getAnswer2Count(), analyticsList.get(2).getAnswer3Count(), analyticsList.get(2).getAnswer4Count());
		Analytics analytics4 = new Analytics(analyticsList.get(3).getLecturerID(), analyticsList.get(3).getQuestionID(), analyticsList.get(3).getAnswer1Count(), analyticsList.get(3).getAnswer2Count(), analyticsList.get(3).getAnswer3Count(), analyticsList.get(3).getAnswer4Count());
		Analytics analytics5 = new Analytics(analyticsList.get(4).getLecturerID(), analyticsList.get(4).getQuestionID(), analyticsList.get(4).getAnswer1Count(), analyticsList.get(4).getAnswer2Count(), analyticsList.get(4).getAnswer3Count(), analyticsList.get(4).getAnswer4Count());


		String str = "{\"chart\": {\"type\":\""+ chartType +"\",\"data\":"
				+ "{\"labels\":[\"Question 1\",\"Question 2\",\"Question 3\",\"Question 4\",\"Question 5\"],"
				+ "\"datasets\":[{\"label\":\"Always\",\"backgroundColor\":\"rgba(12, 240, 145, 0.5)\",\"borderColor\":\"rgb(19, 209, 130)\",\"borderWidth\":1,\"data\":["+ analytics1.getAnswer1Count() +","+ analytics2.getAnswer1Count() +","+ analytics3.getAnswer1Count() +","+ analytics4.getAnswer1Count() +","+ analytics5.getAnswer1Count() +"]},"
						+ "{\"label\":\"Usually\",\"backgroundColor\":\"rgba(54, 162, 235, 0.5)\",\"borderColor\":\"rgb(54, 162, 235)\",\"borderWidth\":1,\"data\":["+ analytics1.getAnswer1Count() +","+ analytics2.getAnswer1Count() +","+ analytics3.getAnswer1Count() +","+ analytics4.getAnswer1Count() +","+ analytics5.getAnswer1Count() +"]},"
						+ "{\"label\":\"Some Times\",\"backgroundColor\":\"rgba(229, 245, 10, 0.5)\",\"borderColor\":\"rgb(213, 227, 27)\",\"borderWidth\":1,\"data\":["+ analytics1.getAnswer1Count() +","+ analytics2.getAnswer1Count() +","+ analytics3.getAnswer1Count() +","+ analytics4.getAnswer1Count() +","+ analytics5.getAnswer1Count() +"]},"
						+ "{\"label\":\"Not At All\",\"backgroundColor\":\"rgba(255, 5, 5, 0.5)\",\"borderColor\":\"rgb(227, 27, 27)\",\"borderWidth\":1,\"data\":["+ analytics1.getAnswer1Count() +","+ analytics2.getAnswer1Count() +","+ analytics3.getAnswer1Count() +","+ analytics4.getAnswer1Count() +","+ analytics5.getAnswer1Count() +"]}]},"
						+ "\"options\":{\"responsive\":true,\"legend\":{\"position\":\"top\"},\"title\":{\"display\":true,\"text\":\"Analytics Chart\"}}}}";
		
	
		HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(str))
				.uri(URI.create("https://quickchart.io/chart/create")).setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		String url=null;
		
			JSONObject jsonObject= new JSONObject(response.body());
			url=(String) jsonObject.get("url");
			System.out.println(url);
			
			URL url_ = new URL(url);
			InputStream is = url_.openStream();
			OutputStream os = new FileOutputStream("chart.jpg");

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

		return url;
	}
	

}
