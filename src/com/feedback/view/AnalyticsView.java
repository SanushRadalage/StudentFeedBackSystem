package com.feedback.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.feedback.controller.QuestionnaireController;
import com.feedback.model.Analytics;
import com.feedback.model.LecturerInformation;

import java.awt.Choice;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.awt.event.ItemEvent;

public class AnalyticsView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 283595486056632426L;
	private JPanel contentPane;

	QuestionnaireController questionnaireController = new QuestionnaireController();
	private int selectedLecturerId = 1;
	private String link;
	private Image image;
	private URL url;
	private List<LecturerInformation> lecturerList;
	private List<Analytics> getAnalyticsList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
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
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public AnalyticsView() throws Exception {

//		String test = questionnaireController.cartConfiguration();
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
		lblNewLabel_1.setBounds(39, 89, 1017, 714);
		contentPane.add(lblNewLabel_1);

		try {
			link = questionnaireController.cartConfiguration("bar", getAnalyticsList);
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
					link = questionnaireController.cartConfiguration("bar", getAnalyticsList);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		choice.setBounds(184, 34, 108, 18);
		for (int i = 0; i < lecturerList.size(); i++) {
			choice.add(lecturerList.get(i).getName());
		}
		contentPane.add(choice);

		JLabel lblNewLabel = new JLabel("Lecturer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel.setBounds(39, 34, 146, 23);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Sign Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		btnNewButton_1.setBounds(991, 34, 87, 21);
		contentPane.add(btnNewButton_1);
	}
}
