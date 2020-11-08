package com.feedback.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.feedback.controller.QuestionnaireController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSignUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2763124822272080102L;
	
	QuestionnaireController questionnaireController = new QuestionnaireController();

	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSignUp frame = new AdminSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminSignUp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 250, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(questionnaireController.signIn(textField.getText(), passwordField.getText())) {
						AnalyticsView analyticView = new AnalyticsView();
						analyticView.setVisible(true);
						AdminSignUp.this.dispose();
					} else {
						  JDialog.setDefaultLookAndFeelDecorated(true);
						    int response = JOptionPane.showConfirmDialog(null, "Invalid credentials, Do you want to try again?", "warning!",
						        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						    if (response == JOptionPane.NO_OPTION) {}
						    else if (response == JOptionPane.YES_OPTION) {
						    	textField.setText(null);
						    	passwordField.setText(null);
						    }
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
			}
		});
		btnNewButton.setAlignmentY(0.0f);
		btnNewButton.setForeground(new Color(51, 0, 51));
		btnNewButton.setBorder(new LineBorder(new Color(51, 0, 51), 2, true));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(434, 266, 98, 35);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		contentPane.add(btnNewButton);

		
		textField = new JTextField();
		textField.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(437, 91, 180, 35);
		textField.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(437, 157, 180, 35);
		passwordField.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		contentPane.add(passwordField);
		
		lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(329, 97, 83, 24);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(329, 163, 76, 24);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_4 = new JLabel("Please signin");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(81, 202, 148, 35);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("to get feedback analatics");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(31, 232, 233, 35);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IntroPage introPage =new IntroPage();
				introPage.setVisible(true);
				AdminSignUp.this.dispose();
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon(AdminSignUp.class.getResource("/img/back.png")));
		lblNewLabel_6.setBackground(new Color(240, 240, 240));
		lblNewLabel_6.setBounds(8, 10, 42, 35);
		contentPane.add(lblNewLabel_6);
	
	
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(AdminSignUp.class.getResource("/img/padlock.png")));
		lblNewLabel_3.setBounds(94, 48, 128, 144);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdminSignUp.class.getResource("/img/sign.png")));
		lblNewLabel.setBounds(0, 0, 304, 413);
		contentPane.add(lblNewLabel);
	
		
	}
}
