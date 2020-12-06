package com.feedback.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 * This is the on-boarding screen.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class IntroPage extends JFrame {

	private static final long serialVersionUID = 7509028678520003425L;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroPage frame = new IntroPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor creates the frame.
	 */
	public IntroPage() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1300, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Get Started");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(51, 0, 51));
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 23));
		btnNewButton.setBorder(new LineBorder(new Color(51, 0, 51), 2, true));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionnaireView questionnaire;
				try {
					questionnaire = new QuestionnaireView();
					questionnaire.setVisible(true);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				IntroPage.this.dispose();
			}
		});
		btnNewButton.setBounds(151, 419, 170, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign In");
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBorder(new LineBorder(new Color(51, 0, 51), 2, true));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSignUp adminSignUp = new AdminSignUp();
				adminSignUp.setVisible(true);
				IntroPage.this.dispose();
				
			}
		});
		btnNewButton_1.setBounds(1181, 26, 88, 39);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 1284, 712);
		lblNewLabel.setIcon(new ImageIcon(IntroPage.class.getResource("/img/intro.png")));
		contentPane.add(lblNewLabel);
		
	}
}
