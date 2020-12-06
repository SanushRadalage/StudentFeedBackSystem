package com.feedback.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import com.feedback.controller.QuestionnaireController;
import com.feedback.model.Analytics;
import com.feedback.model.Lecturer;
import com.feedback.model.Questionnaire;
import com.feedback.model.SelectedAnswers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * This GUI retrieve lecturer list and question list from server and show to the
 * user. User need to choose one particular lecturer and provide answers. User
 * can't skip any question. Provided answers keep in memory as a list until user
 * submit. After the submission application will update the answer counts in
 * database through the server.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */

public class QuestionnaireView extends JFrame {

	private static final long serialVersionUID = 3521136443067340974L;
	private JPanel contentPane;

	private static int position = 0;
	private String answer = null;
	private int selectedLecturerId = 1;
	private static Questionnaire currentQuestion;
	private static int currentQuestionId;

	private int answerId;

	private List<SelectedAnswers> selectdAnswers = new ArrayList<>();
	private List<Analytics> getAnalyticsList = new ArrayList<>();
	private List<Lecturer> lecturerList;
	private List<Questionnaire> questionList;
	QuestionnaireController questionnaireController = new QuestionnaireController();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionnaireView frame = new QuestionnaireView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor creates the frame.
	 * 
	 * @throws Exception
	 */
	public QuestionnaireView() throws Exception {

		lecturerList = questionnaireController.lecturerList();
		questionList = questionnaireController.getQuestions();
		currentQuestion = questionList.get(position);
		currentQuestionId = questionList.get(position).getQuestionId();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(78, 10, 616, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select Lecturer's Name : ");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		lblNewLabel.setBounds(8, 16, 213, 19);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dr. / Mr. / Mrs. / Ms. ");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(229, 16, 165, 19);
		panel_1.add(lblNewLabel_1);

		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedLecturerId = choice.getSelectedIndex() + 1;
			}
		});
		choice.setFont(new Font("Bahnschrift", Font.PLAIN, 16));

		for (int i = 0; i < lecturerList.size(); i++) {
			choice.add(lecturerList.get(i).getName());
		}

		choice.setBounds(422, 10, 184, 25);
		choice.setBackground(new Color(255, 255, 255));
		panel_1.add(choice);

		JLabel lblNewLabel_2 = new JLabel(currentQuestion.getQuestion());
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(18, 88, 760, 61);
		contentPane.add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(85, 191, 609, 144);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel extremeImg = new JLabel("");
		extremeImg.setBounds(10, 5, 64, 64);
		extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smilingB.png")));
		panel.add(extremeImg);

		JLabel agreeImg = new JLabel("");
		agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smileB.png")));
		agreeImg.setBounds(180, 5, 64, 64);
		panel.add(agreeImg);

		JLabel maybeImg = new JLabel("");
		maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curiousB.png")));
		maybeImg.setBounds(350, 5, 64, 64);
		panel.add(maybeImg);

		JLabel noImg = new JLabel("");
		noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutralB.png")));
		noImg.setBounds(520, 5, 64, 64);
		panel.add(noImg);

		JLabel lblNewLabel_4 = new JLabel(currentQuestion.getAnswer1());
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 88, 78, 20);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(currentQuestion.getAnswer2());
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(178, 88, 78, 20);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(currentQuestion.getAnswer3());
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(335, 88, 94, 20);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel(currentQuestion.getAnswer4());
		lblNewLabel_7.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(517, 88, 88, 20);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				position = 0;
				answer = null;
				currentQuestion = null;
				questionList.clear();
				IntroPage introPage = new IntroPage();
				introPage.setVisible(true);
				QuestionnaireView.this.dispose();
			}
		});
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/back.png")));
		lblNewLabel_3.setBounds(8, 10, 44, 45);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_8 = new JLabel("01");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_8.setBackground(new Color(0, 204, 153));
		lblNewLabel_8.setBounds(360, 423, 57, 30);
		contentPane.add(lblNewLabel_8);

		JButton btnNewButton_2 = new JButton("Finish");
		btnNewButton_2.setVisible(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					getAnalyticsList = questionnaireController.getAnalytics(selectedLecturerId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				SelectedAnswers selectedAnswer = new SelectedAnswers(currentQuestionId, answerId);
				selectdAnswers.add(selectedAnswer);

				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Do you want to submit the feedback?", "Thank you",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
				} else if (response == JOptionPane.YES_OPTION) {

					for (int i = 0; i < selectdAnswers.size(); i++) {

						Analytics analytics = new Analytics();

						int answer1count = 0;
						int answer2count = 0;
						int answer3count = 0;
						int answer4count = 0;

						if (getAnalyticsList != null) {

							answer1count = getAnalyticsList.get(i).getAnswer1Count();
							answer2count = getAnalyticsList.get(i).getAnswer2Count();
							answer3count = getAnalyticsList.get(i).getAnswer3Count();
							answer4count = getAnalyticsList.get(i).getAnswer4Count();

						}

						analytics.setLecturerID(selectedLecturerId);
						analytics.setQuestionID(selectdAnswers.get(i).getQuestionId());

						if (selectdAnswers.get(i).getSelectedAnswerId() == 1) {
							analytics.setAnswer1Count(answer1count + 1);
							analytics.setAnswer2Count(answer2count);
							analytics.setAnswer3Count(answer3count);
							analytics.setAnswer4Count(answer4count);

						} else if (selectdAnswers.get(i).getSelectedAnswerId() == 2) {
							analytics.setAnswer2Count(answer2count + 1);
							analytics.setAnswer1Count(answer1count);
							analytics.setAnswer3Count(answer3count);
							analytics.setAnswer4Count(answer4count);

						} else if (selectdAnswers.get(i).getSelectedAnswerId() == 3) {
							analytics.setAnswer3Count(answer3count + 1);
							analytics.setAnswer1Count(answer1count);
							analytics.setAnswer2Count(answer2count);
							analytics.setAnswer4Count(answer4count);

						} else if (selectdAnswers.get(i).getSelectedAnswerId() == 4) {
							analytics.setAnswer4Count(answer4count + 1);
							analytics.setAnswer1Count(answer1count);
							analytics.setAnswer1Count(answer2count);
							analytics.setAnswer1Count(answer3count);
						}

						try {
							questionnaireController.updateAnalytics(analytics);
						} catch (Exception e1) {
							System.out.println(e1);
						}

					}

					position = 0;
					answer = null;
					currentQuestion = null;
					questionList.clear();
					IntroPage introPage = new IntroPage();
					introPage.setVisible(true);
					QuestionnaireView.this.dispose();
				} else if (response == JOptionPane.CLOSED_OPTION) {
				}

			}
		});
		btnNewButton_2.setBorder(new LineBorder(new Color(51, 0, 51), 1, true));
		btnNewButton_2.setBackground(new Color(51, 0, 51));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnNewButton_2.setBounds(678, 419, 100, 34);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("Next");

		if (answer == null) {
			btnNewButton_1.setEnabled(false);
		} else {
			btnNewButton_1.setEnabled(true);
		}

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnNewButton_1.setEnabled(false);

				SelectedAnswers selectedAnswer = new SelectedAnswers(currentQuestionId, answerId);
				selectdAnswers.add(selectedAnswer);

				position++;

				if (position < questionList.size()) {
					currentQuestion = questionList.get(position);
					currentQuestionId = questionList.get(position).getQuestionId();
					lblNewLabel_2.setText(currentQuestion.getQuestion());
					lblNewLabel_4.setText(currentQuestion.getAnswer1());
					lblNewLabel_5.setText(currentQuestion.getAnswer2());
					lblNewLabel_6.setText(currentQuestion.getAnswer3());
					lblNewLabel_7.setText(currentQuestion.getAnswer4());

					extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smilingB.png")));
					agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smileB.png")));
					maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curiousB.png")));
					noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutralB.png")));

					answer = null;

					int questionNumber = position + 1;

					lblNewLabel_8.setText("0" + Integer.toString(questionNumber));

				}

				if (position + 1 == questionList.size()) {
					btnNewButton_2.setVisible(true);
				} else {
					btnNewButton_2.setVisible(false);
				}

				if (position != 0) {
					panel_1.setVisible(false);
				}

			}
		});

		btnNewButton_1.setBorder(new LineBorder(new Color(51, 0, 51), 2, true));
		btnNewButton_1.setBackground(new Color(51, 0, 51));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnNewButton_1.setBounds(678, 419, 100, 34);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Previous");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (position > 0) {
					position--;
					lblNewLabel_8.setText("0" + Integer.toString(position + 1));

					if (position == 0)
						panel_1.setVisible(true);
				}

				if (position + 1 == questionList.size()) {
					btnNewButton_2.setVisible(true);
				} else {
					btnNewButton_2.setVisible(false);
				}

				currentQuestion = questionList.get(position);
				currentQuestionId = questionList.get(position).getQuestionId();
				lblNewLabel_2.setText(currentQuestion.getQuestion());
				lblNewLabel_4.setText(currentQuestion.getAnswer1());
				lblNewLabel_5.setText(currentQuestion.getAnswer2());
				lblNewLabel_6.setText(currentQuestion.getAnswer3());
				lblNewLabel_7.setText(currentQuestion.getAnswer4());

				extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smilingB.png")));
				agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smileB.png")));
				maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curiousB.png")));
				noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutralB.png")));

			}
		});

		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBorder(new LineBorder(new Color(51, 0, 51), 2, true));
		btnNewButton.setBackground(new Color(51, 0, 51));
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnNewButton.setBounds(8, 419, 100, 34);
		contentPane.add(btnNewButton);

		extremeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				answerId = 1;
				extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smiling.png")));
				agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smileB.png")));
				maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curiousB.png")));
				noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutralB.png")));

				answer = lblNewLabel_4.getText();
				btnNewButton_1.setEnabled(true);

			}
		});

		agreeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				answerId = 2;
				agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smile.png")));
				extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smilingB.png")));
				maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curiousB.png")));
				noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutralB.png")));

				answer = lblNewLabel_5.getText();
				btnNewButton_1.setEnabled(true);

			}
		});

		maybeImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				answerId = 3;
				maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curious.png")));
				extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smilingB.png")));
				agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smileB.png")));
				noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutralB.png")));

				answer = lblNewLabel_6.getText();
				btnNewButton_1.setEnabled(true);

			}
		});

		noImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				answerId = 4;
				noImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/neutral.png")));
				extremeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smilingB.png")));
				agreeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/smileB.png")));
				maybeImg.setIcon(new ImageIcon(QuestionnaireView.class.getResource("/img/curiousB.png")));

				answer = lblNewLabel_7.getText();
				btnNewButton_1.setEnabled(true);

			}
		});

	}
}
