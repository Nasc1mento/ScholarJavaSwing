package org.scholar.oop.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.scholar.oop.gui.FormLogin.EnterDashboard;
import org.scholar.oop.gui.FormLogin.EnterHome;

public class FormRegister extends JFrame{
	
	private JButton buttonRegister;
	private JButton buttonReturnHome;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;
	private JTextField textFieldConfirmPassword;
	private JPanel panelButtonForm;
	private JPanel panelForm;
	private JLabel labelEmail;
	private JLabel labelPassword;
	private JLabel labelName;
	private JLabel labelConfirmPassword;
	
	public FormRegister() {
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(900,500);
		this.setVisible(true);
		this.createForm();
		this.createButtonsForm();
	}
	
	public void createForm() {
		
		this.panelForm = new JPanel();
		this.textFieldName = new JTextField(35);
		this.textFieldEmail = new JTextField(30);
		this.textFieldPassword = new JTextField(25);
		this.textFieldConfirmPassword = new JTextField(25);
		this.labelName = new JLabel("Name");
		this.labelEmail = new JLabel("Email");
		this.labelPassword = new JLabel("Password");
		this.labelConfirmPassword = new JLabel("Confirm Password");
		
		
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
		
		this.add(this.labelName);
		this.add(this.textFieldName);
		this.add(this.labelEmail);
		this.add(this.textFieldEmail);
		this.add(this.labelPassword);
		this.add(this.textFieldPassword);
		this.add(this.labelConfirmPassword);
		this.add(this.textFieldConfirmPassword);
		
		this.getContentPane().add(this.panelForm, BorderLayout.CENTER);
		
	}
	
	public void createButtonsForm() {
		this.panelButtonForm = new JPanel();
		this.buttonReturnHome = new JButton("Back to Home");
		this.buttonRegister = new JButton("Register");
		
		this.panelButtonForm.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		this.buttonRegister.addActionListener(new RegisterUserAndReturnHome());
		this.buttonReturnHome.addActionListener(new EnterHome());
		
		this.panelButtonForm.add(buttonRegister);
		this.panelButtonForm.add(buttonReturnHome);
		
		getContentPane().add(this.panelButtonForm, 
				BorderLayout.SOUTH);
		
	}
	
	public class RegisterUserAndReturnHome implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//if true
			
			
		}
		
	}
	
	public class EnterHome implements ActionListener{

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
