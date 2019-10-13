package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Admin_con;
import Tools.ReadData;

/**
 * Admin find user to check details GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class FindUser extends JFrame {
	
	private JLabel noticeLabel;
	private JTextField QMField;
	private JButton cancelButton, confirmButton;
	private JPanel panel1;
	private JButton user[];
	private String input,s, as[];
	
	public FindUser() {
		
		super("Finding User");
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		noticeLabel = new JLabel("Please enter the QM ID you want to view", JLabel.CENTER);
		
		QMField = new JTextField(20);
		
		cancelButton = new JButton("Cancel");
		confirmButton = new JButton("Confirm");
		
		panel1 = new JPanel(new FlowLayout());
		panel1.add(cancelButton);	panel1.add(confirmButton);
		
		container.add(noticeLabel);
		container.add(QMField);
		container.add(panel1);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 370);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionCancel();
		actionFind();
	}
	
	public void actionFind() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String QMID = QMField.getText();
				Admin_con admin_con = new Admin_con();
				if(admin_con.find_user(QMID)) {
					FindUserDetail user = new FindUserDetail(QMID);
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Invalid ID! Please enter again!");
					QMField.setText("");
					QMField.requestFocus();
				}
			
				
			}
		});
	}
	
	public void actionCancel() {
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminMenu menu = new AdminMenu();
				menu.setVisible(true);
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}

/*	public static void main(String[] args) {
		FindUser user = new FindUser();
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}*/
	
}
