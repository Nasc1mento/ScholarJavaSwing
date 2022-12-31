package org.social.oop.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.social.oop.exception.EmailFieldNotFilledException;
import org.social.oop.exception.PasswordNotMatchException;
import org.social.oop.exception.UserNotRegisteredException;
import org.social.oop.gui.components.ButtonComponent;
import org.social.oop.gui.components.FrameComponent;
import org.social.oop.model.User;
import org.social.oop.persistence.UserDAO;

public class Login extends FrameComponent{
	
	private JButton buttonLogin;
	private JButton buttonReturnHome;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JPanel panelButtonForm;
	private JPanel panelForm;
	private JLabel labelUsername;
	private JLabel labelPassword;
	
	
	public Login() {
		
		this.setTitle("OOPSocial/Login");
		
		this.createForm();
		this.createButtonsForm();
		
	}
	
	
	
	
	public void createForm() {
		
		this.panelForm = new JPanel();
		this.textFieldUsername = new JTextField(43);
		this.passwordFieldPassword = new JPasswordField(43);
		this.labelUsername = new JLabel("Username");
		this.labelPassword = new JLabel("Password");
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		
		
		this.add(this.labelUsername);
		this.add(this.textFieldUsername);
		this.add(this.labelPassword);
		this.add(this.passwordFieldPassword);
		
		
		this.getContentPane().add(this.panelForm, BorderLayout.CENTER);
	
		
	}
	
	public void createButtonsForm() {
		this.panelButtonForm = new JPanel();
		this.buttonReturnHome = new ButtonComponent("Back to Home");
		this.buttonLogin = new ButtonComponent("Login");
		
		this.panelButtonForm.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		this.buttonLogin.addActionListener(new DashboardListener());
		this.buttonReturnHome.addActionListener(new HomeListener());
		
		this.panelButtonForm.add(buttonLogin);
		this.panelButtonForm.add(buttonReturnHome);
		
		getContentPane().add(this.panelButtonForm, 
				BorderLayout.SOUTH);
		
	}
	
	
	public class DashboardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UserDAO.getInstance().authUser(new User(textFieldUsername.getText(),passwordFieldPassword.getText()));
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							dispose();
							new Dashboard();
						}
					});
			}catch(UserNotRegisteredException | EmailFieldNotFilledException | PasswordNotMatchException exception) {
				JOptionPane.showMessageDialog(null, exception.getMessage());
			}
		}
	}
	
	public class HomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					dispose();
					new Home();					
				}
			});
		}
	}
}
