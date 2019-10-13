package Interface;

import javax.swing.*;

import Control.Validation;

import Tools.ReadData;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

/**
 * Log in GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class Login extends JFrame {

	private JLabel noticeLabel, QMIDLabel; 
	private JButton confirmButton;
	private JTextField QMid;
	private JPanel buttonPanel;
	//private JPasswordField pass = new JPasswordField(15);

	public Login() {
		super("User_Login");	//set title
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		noticeLabel = new JLabel("Welcome to Scooter Sharing System", JLabel.CENTER);	//welcome title
		JLabel QMIDLabel = new JLabel("User ID: ", JLabel.LEFT);
		confirmButton = new JButton("Confirm");
		buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		
		QMid = new JTextField(15);
		//panel.setLayout (null);

		JPanel QM = new JPanel(new FlowLayout());
		QM.add(QMIDLabel);
		QM.add(QMid);
		
		//JPanel panel = new JPanel(new GridLayout(3,1));
		container.add(noticeLabel);
		container.add(QM);
		container.add(buttonPanel);
		
		//panel.add(pass);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window

		actionlogin();
			
	}

	public void actionlogin(){
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String QMnum = QMid.getText();
				Validation log = new Validation(QMnum);
				
				if(log.login_stu()) {
					SelectStation selectstation = new SelectStation(QMnum);
					selectstation.setVisible(true);
					selectstation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				} 
				else if(log.login_admin()) {
					AdminMenu adminmenu = new AdminMenu();
					adminmenu.setVisible(true);
					adminmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Wrong Username");
					QMid.setText("");
					QMid.requestFocus();
				}

			}
		});
	}
	
	public static void main(String[] args) {
		Login frameTabel = new Login();
		frameTabel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}