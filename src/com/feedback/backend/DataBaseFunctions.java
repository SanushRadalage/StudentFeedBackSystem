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
		// TODO Auto-generated method stub
		return null;
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
	public String chartConfigure() throws Exception {
		HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

		String str = "{\"chart\": {\"type\":\"bar\",\"data\":{\"labels\":[\"Question 1\",\"Question 2\",\"Question 3\",\"Question 4\",\"Question 5\"],\"datasets\":[{\"label\":\"Always\",\"backgroundColor\":\"rgba(12, 240, 145, 0.5)\",\"borderColor\":\"rgb(19, 209, 130)\",\"borderWidth\":1,\"data\":[31,70,30,33,9]},{\"label\":\"Usually\",\"backgroundColor\":\"rgba(54, 162, 235, 0.5)\",\"borderColor\":\"rgb(54, 162, 235)\",\"borderWidth\":1,\"data\":[73,41,29,61,65]},{\"label\":\"Some Times\",\"backgroundColor\":\"rgba(229, 245, 10, 0.5)\",\"borderColor\":\"rgb(213, 227, 27)\",\"borderWidth\":1,\"data\":[40,31,35,51,15]},{\"label\":\"Not At All\",\"backgroundColor\":\"rgba(255, 5, 5, 0.5)\",\"borderColor\":\"rgb(227, 27, 27)\",\"borderWidth\":1,\"data\":[40,31,35,51,15]}]},\"options\":{\"responsive\":true,\"legend\":{\"position\":\"top\"},\"title\":{\"display\":true,\"text\":\"Analytics Chart\"}}}}";

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
