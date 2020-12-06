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
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONObject;

import com.feedback.interfaces.RemoteInterface;
import com.feedback.model.Admin;
import com.feedback.model.Analytics;
import com.feedback.model.Lecturer;
import com.feedback.model.Questionnaire;

/**
 * This class handle the Database connection and facilitate to the
 * implementation of the RemoteInterface for CRUD operations. This class bind
 * into a registry as a remote object.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class DataBaseFunctions extends UnicastRemoteObject implements RemoteInterface {

	private static final long serialVersionUID = -9088567564909054283L;
	Connection connection = null;
	Statement statement = null;

	/**
	 * Attempts to connect with Database using JDBC driver
	 * 
	 * @throws Exception
	 */
	public DataBaseFunctions() throws Exception {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/feedbackdb", "root", "");
			System.out.print("Connected database & ");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Questionnaire> getQuestions() throws Exception {
		ArrayList<Questionnaire> questionnaireList = new ArrayList<>();

		String sql = "SELECT * FROM questions";

		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Questionnaire questionnaire = new Questionnaire(resultSet.getInt("QuestionID"),
					resultSet.getString("Question"), resultSet.getString("Answer1"), resultSet.getString("Answer2"),
					resultSet.getString("Answer3"), resultSet.getString("Answer4"));
			questionnaireList.add(questionnaire);
		}

		resultSet.close();

		return questionnaireList;
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Lecturer> lecturerList() throws Exception {

		ArrayList<Lecturer> lecturersList = new ArrayList<>();

		String sql = "SELECT * FROM lecturer";

		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Lecturer lecturerInformation = new Lecturer(resultSet.getInt("LecturerID"),
					resultSet.getString("LecturerName"));
			lecturersList.add(lecturerInformation);
		}

		resultSet.close();

		return lecturersList;
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public Boolean signInData(Admin admin) throws Exception {
		ResultSet resultSet = null;

		statement = connection.createStatement();
		String sql = "SELECT * FROM admin WHERE UserName ='" + admin.getName() + "' AND Password ='"
				+ admin.getPassword() + "'";

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

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public void setAnalytics(Analytics analytics) throws Exception {
		statement = connection.createStatement();

		String sql = "UPDATE analytics SET Answer1Count = '" + analytics.getAnswer1Count() + "', Answer2Count = " + " '"
				+ analytics.getAnswer2Count() + "', Answer3Count = " + " '" + analytics.getAnswer3Count()
				+ "', Answer4Count = " + " '" + analytics.getAnswer4Count() + "' WHERE LecturerID = '"
				+ analytics.getLecturerID() + "'AND QuestionID = '" + analytics.getQuestionID() + "'";

		statement.executeUpdate(sql);

	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Analytics> getAnalytics(int lecId) throws Exception {

		ArrayList<Analytics> analyticsList = new ArrayList<>();

		String sql = "SELECT * FROM analytics WHERE LecturerID = '" + lecId + "'";

		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Analytics analytics = new Analytics(resultSet.getInt("LecturerID"), resultSet.getInt("QuestionID"),
					resultSet.getInt("Answer1Count"), resultSet.getInt("Answer2Count"),
					resultSet.getInt("Answer3Count"), resultSet.getInt("Answer4Count"));
			analyticsList.add(analytics);
		}

		return analyticsList;
	}

	/**
	 * Implementation of remote interface method.
	 * 
	 * Oracle Corporation, "OpenJDK," Oracle Corporation, 2020. [Online]. Available:
	 * https://openjdk.java.net/groups/net/httpclient/recipes.html. [Accessed 2020].
	 */
	@Override
	public String chartConfigure(String chartType, List<Analytics> analyticsList, int questionId) throws Exception {
		HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

		Analytics analytics1 = new Analytics(analyticsList.get(0).getLecturerID(), analyticsList.get(0).getQuestionID(),
				analyticsList.get(0).getAnswer1Count(), analyticsList.get(0).getAnswer2Count(),
				analyticsList.get(0).getAnswer3Count(), analyticsList.get(0).getAnswer4Count());
		Analytics analytics2 = new Analytics(analyticsList.get(1).getLecturerID(), analyticsList.get(1).getQuestionID(),
				analyticsList.get(1).getAnswer1Count(), analyticsList.get(1).getAnswer2Count(),
				analyticsList.get(1).getAnswer3Count(), analyticsList.get(1).getAnswer4Count());
		Analytics analytics3 = new Analytics(analyticsList.get(2).getLecturerID(), analyticsList.get(2).getQuestionID(),
				analyticsList.get(2).getAnswer1Count(), analyticsList.get(2).getAnswer2Count(),
				analyticsList.get(2).getAnswer3Count(), analyticsList.get(2).getAnswer4Count());
		Analytics analytics4 = new Analytics(analyticsList.get(3).getLecturerID(), analyticsList.get(3).getQuestionID(),
				analyticsList.get(3).getAnswer1Count(), analyticsList.get(3).getAnswer2Count(),
				analyticsList.get(3).getAnswer3Count(), analyticsList.get(3).getAnswer4Count());
		Analytics analytics5 = new Analytics(analyticsList.get(4).getLecturerID(), analyticsList.get(4).getQuestionID(),
				analyticsList.get(4).getAnswer1Count(), analyticsList.get(4).getAnswer2Count(),
				analyticsList.get(4).getAnswer3Count(), analyticsList.get(4).getAnswer4Count());

		String str = "";

		if (chartType.equals("bar")) {

			str = "{\"chart\": {\"type\":\"" + chartType + "\",\"data\":"
					+ "{\"labels\":[\"Question 1\",\"Question 2\",\"Question 3\",\"Question 4\",\"Question 5\"],"
					+ "\"datasets\":[{\"label\":\"Always\",\"backgroundColor\":\"rgba(12, 240, 145, 0.5)\",\"borderColor\":\"rgb(19, 209, 130)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer1Count() + "," + analytics2.getAnswer1Count() + ","
					+ analytics3.getAnswer1Count() + "," + analytics4.getAnswer1Count() + ","
					+ analytics5.getAnswer1Count() + "]},"
					+ "{\"label\":\"Usually\",\"backgroundColor\":\"rgba(54, 162, 235, 0.5)\",\"borderColor\":\"rgb(54, 162, 235)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer2Count() + "," + analytics2.getAnswer2Count() + ","
					+ analytics3.getAnswer2Count() + "," + analytics4.getAnswer2Count() + ","
					+ analytics5.getAnswer2Count() + "]},"
					+ "{\"label\":\"Some Times\",\"backgroundColor\":\"rgba(229, 245, 10, 0.5)\",\"borderColor\":\"rgb(213, 227, 27)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer3Count() + "," + analytics2.getAnswer3Count() + ","
					+ analytics3.getAnswer3Count() + "," + analytics4.getAnswer3Count() + ","
					+ analytics5.getAnswer3Count() + "]},"
					+ "{\"label\":\"Not At All\",\"backgroundColor\":\"rgba(255, 5, 5, 0.5)\",\"borderColor\":\"rgb(227, 27, 27)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer4Count() + "," + analytics2.getAnswer4Count() + ","
					+ analytics3.getAnswer4Count() + "," + analytics4.getAnswer4Count() + ","
					+ analytics5.getAnswer4Count() + "]}]},"
					+ "\"options\":{\"responsive\":true,\"legend\":{\"position\":\"top\"},\"title\":{\"display\":true,\"text\":\"Analytics Chart\"}}}}";
		} else {

			Analytics analytics = null;

			if (questionId == 1) {
				analytics = analytics1;
			} else if (questionId == 2) {
				analytics = analytics2;
			} else if (questionId == 3) {
				analytics = analytics3;
			} else if (questionId == 4) {
				analytics = analytics4;
			} else if (questionId == 5) {
				analytics = analytics5;
			}

			str = "{\"chart\": {\"type\":\"" + chartType + "\",\"data\":{\"datasets\":[{\"data\":["
					+ analytics.getAnswer1Count() + "," + analytics.getAnswer2Count() + ","
					+ analytics.getAnswer3Count() + "," + analytics.getAnswer4Count()
					+ "],\"backgroundColor\":[\"rgb(12, 240, 145)\",\"rgb(54, 162, 235)\",\"rgb(229, 245, 10)\",\"rgb(255, 5, 5)\"],\"label\":\"Dataset 1\"}],\"labels\":[\"Always\",\"Usually\",\"Some Times\",\"Not At All\"]}}}";
		}

		HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(str))
				.uri(URI.create("https://quickchart.io/chart/create")).setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		String url = null;

		JSONObject jsonObject = new JSONObject(response.body());
		url = (String) jsonObject.get("url");

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

	/**
	 * Implementation of remote interface method.
	 * 
	 * This method create a new session with mail authenticator. If the
	 * authentication success it will able to create email and send to particular
	 * mail address.
	 * 
	 * JavaTpoint, "JavaTpoint," JavaTpoint, 2018. [Online]. Available:
	 * https://www.javatpoint.com/example-of-sending-email-using-java-mail-api.
	 * [Accessed 2020].
	 */
	@Override
	public void sendEmailWithAttachments(String toAddress) throws AddressException, MessagingException {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", "sender@gmail.com");
		properties.put("mail.password", "password");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sender@gmail.com", "password");
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("sender@gmail.com"));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject("feedback Analytics reports");
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Please find the attachments.", "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		MimeBodyPart attachPart = new MimeBodyPart();

		try {
			attachPart.attachFile("D:\\sliit\\comparative\\XUniFeedBackSystem\\chart.jpg");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);

	}

}
