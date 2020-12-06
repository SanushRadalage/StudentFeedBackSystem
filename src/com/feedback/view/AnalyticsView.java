package com.feedback.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.feedback.controller.QuestionnaireController;
import com.feedback.model.Analytics;
import com.feedback.model.Lecturer;

import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Color;

import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;

/**
 * This GUI provide option to admin to see bar charts, pie charts and also send
 * those charts for a particular mail address through the server.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class AnalyticsView extends JFrame {

	private static final long serialVersionUID = 283595486056632426L;
	private JPanel contentPane;

	QuestionnaireController questionnaireController = new QuestionnaireController();
	private int selectedLecturerId = 1;
	private String link;
	private Image image;
	private URL url;
	private List<Lecturer> lecturerList;
	private List<Analytics> getAnalyticsList = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyticsView frame = new AnalyticsView();
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
	public AnalyticsView() throws Exception {

		lecturerList = questionnaireController.lecturerList();

		try {
			getAnalyticsList = questionnaireController.getAnalytics(selectedLecturerId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1100, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(35, 85, 1020, 710);
		contentPane.add(lblNewLabel_1);

		try {
			link = questionnaireController.cartConfiguration("bar", getAnalyticsList, 0);
			url = new URL(link);
			image = ImageIO.read(url);
			lblNewLabel_1.setIcon(new ImageIcon(image));

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedLecturerId = choice.getSelectedIndex() + 1;
				try {
					getAnalyticsList = questionnaireController.getAnalytics(selectedLecturerId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					link = questionnaireController.cartConfiguration("bar", getAnalyticsList, 0);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		choice.setBounds(130, 20, 108, 18);
		for (int i = 0; i < lecturerList.size(); i++) {
			choice.add(lecturerList.get(i).getName());
		}
		contentPane.add(choice);

		JLabel lblNewLabel = new JLabel("Lecturer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 20, 106, 23);
		contentPane.add(lblNewLabel);

		JLabel btnNewButton = new JLabel("");
		btnNewButton.setIcon(new ImageIcon(AnalyticsView.class.getResource("/img/email.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					questionnaireController.sendEmailWithAttachments("reciever@gmail.com");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Mail sended successfully");
			}
		});
		btnNewButton.setBounds(965, 20, 40, 40);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IntroPage introPage = new IntroPage();
				introPage.setVisible(true);
				AnalyticsView.this.dispose();
			}
		});
		lblNewLabel_12.setIcon(new ImageIcon(AnalyticsView.class.getResource("/img/logout.png")));
		lblNewLabel_12.setBackground(new Color(240, 240, 240));
		lblNewLabel_12.setBounds(1013, 22, 42, 35);
		contentPane.add(lblNewLabel_12);

		JLabel lblC = new JLabel("Chart Type");
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblC.setBounds(325, 20, 106, 23);
		contentPane.add(lblC);

		JLabel lblNewLabel_12_1 = new JLabel("");
		lblNewLabel_12_1.setBackground(SystemColor.menu);
		lblNewLabel_12_1.setBounds(1003, 20, 42, 35);
		contentPane.add(lblNewLabel_12_1);

		JLabel lblC_1 = new JLabel("Question No");
		lblC_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblC_1.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblC_1.setBounds(562, 20, 106, 23);
		contentPane.add(lblC_1);

		lblC_1.setVisible(false);

		Choice choice_1 = new Choice();
		choice_1.setBounds(674, 20, 259, 18);
		choice_1.add("1 Is the lecturer use VLE to share learning materials with students?");
		choice_1.add("2 How about the student participation in the lecture?");
		choice_1.add("3 Is the lecturer flexible when students ask questions during the lecture?");
		choice_1.add("4 Is lecturer checks to make sure students understand the lecture?");
		choice_1.add("5 The lecturer takes time to summarize the lecture end of the day?");

		choice_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					link = questionnaireController.cartConfiguration("pie", getAnalyticsList,
							choice_1.getSelectedIndex() + 1);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		contentPane.add(choice_1);

		choice_1.setVisible(false);

		JLabel lblNewLabel_12_2 = new JLabel("");
		lblNewLabel_12_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choice_1.setVisible(false);
				lblC_1.setVisible(false);
				try {
					link = questionnaireController.cartConfiguration("bar", getAnalyticsList, 0);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		lblNewLabel_12_2.setIcon(new ImageIcon(AnalyticsView.class.getResource("/img/bar-graph.png")));
		lblNewLabel_12_2.setBackground(SystemColor.menu);
		lblNewLabel_12_2.setBounds(452, 10, 42, 35);
		contentPane.add(lblNewLabel_12_2);

		JLabel lblNewLabel_12_2_1 = new JLabel("");
		lblNewLabel_12_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choice_1.setVisible(true);
				lblC_1.setVisible(true);
				try {
					link = questionnaireController.cartConfiguration("pie", getAnalyticsList, 1);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_12_2_1.setIcon(new ImageIcon(AnalyticsView.class.getResource("/img/pie-chart.png")));
		lblNewLabel_12_2_1.setBackground(SystemColor.menu);
		lblNewLabel_12_2_1.setBounds(512, 10, 42, 35);
		contentPane.add(lblNewLabel_12_2_1);

	}
}
