package com.feedback.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.feedback.controller.QuestionnaireController;

import java.awt.Choice;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class AnalyticsView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 283595486056632426L;
	private JPanel contentPane;
	
	QuestionnaireController questionnaireController = new QuestionnaireController();


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
	 * @throws Exception 
	 */
	public AnalyticsView() throws Exception {
		
		String test = questionnaireController.cartConfiguration();
		System.out.print(test);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1100, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Choice choice = new Choice();
		choice.setBounds(184, 34, 108, 18);
		contentPane.add(choice);
		
		JLabel lblNewLabel = new JLabel("Lecturer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel.setBounds(53, 34, 146, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\sliit\\comparative\\XUniFeedBackSystem\\chart.jpg"));
		lblNewLabel_1.setBounds(39, 67, 1017, 714);
		contentPane.add(lblNewLabel_1);
	}
}
