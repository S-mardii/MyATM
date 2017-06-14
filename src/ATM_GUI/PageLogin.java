package ATM_GUI;

import ATM_Process.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PageLogin extends JFrame{
	public PageLogin() {
		setSize(500, 400);
		setTitle("My ATM - Log In");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//set JFrame at the center of the screen
		Toolkit toolkit = getToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setLocation((screenSize.width-getWidth())/2, (screenSize.height-getHeight())/2);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.gray);
		
		JLabel lbl_appName, lbl_cardNumber, lbl_pinCode;
		lbl_appName = new JLabel("My ATM - Log In Page");
		lbl_cardNumber = new JLabel("Card Number: ");
		lbl_pinCode = new JLabel("PIN Code: ");
		
		lbl_appName.setFont(new Font("Serif", Font.BOLD, 25));
		lbl_appName.setBounds(20, 20, 250, 30);
		
		Font fontFormLabel = new Font("Serif", Font.PLAIN, 20);
		lbl_cardNumber.setFont(fontFormLabel);
		lbl_cardNumber.setBounds(20, 90, 150, 25);
		lbl_pinCode.setFont(fontFormLabel);
		lbl_pinCode.setBounds(20, 140, 150, 25);
		
		JTextField txt_cardNumber = new JTextField();
		txt_cardNumber.setBounds(170, 90, 250, 30);
		
		JPasswordField txt_pinCode = new JPasswordField();
		txt_pinCode.setBounds(170, 140, 250, 30);
		
		JButton btn_login, btn_cancel;
		btn_login = new JButton("Login");
		btn_login.setFont(fontFormLabel);
		
		btn_cancel = new JButton("Cancel");
		btn_cancel.setFont(fontFormLabel);
		
		btn_login.setBounds(280, 280, 90, 50);
		btn_cancel.setBounds(380, 280, 90, 50);
		
		panel.add(lbl_appName, BorderLayout.NORTH);
		panel.add(lbl_cardNumber);
		panel.add(lbl_pinCode);
		
		panel.add(txt_cardNumber);
		panel.add(txt_pinCode);
		
		panel.add(btn_login);
		panel.add(btn_cancel);
	}
	
	public static void main(String[] args) {
		PageLogin login = new PageLogin();
		login.setVisible(true);
	}
}