package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Admin menu GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class AdminMenu extends JFrame {
	
	private JLabel welcomeLabel;
	private JLabel addLabel, removeLabel, usageLabel, statusLabel;
	//private JLabel label_figure = new JLabel(new ImageIcon("src/scooter.png"));
	private JPanel functionPanel, img_functionPanel, buttonPanel;
	private JButton addButton, removeButton, usageButton, statusButton;
	private JButton backButton;

	public AdminMenu(){
		super("Admin_menu");
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		welcomeLabel = new JLabel("Welcome to Scooter Management System", JLabel.CENTER);
		
		addButton = new JButton("1.Add Users");
		removeButton = new JButton("2.Remove Users");
		usageButton = new JButton("3.View Usage of Each User");
		statusButton = new JButton("4.View Status of Stations");
		
		functionPanel = new JPanel(new GridLayout(4,1));
		functionPanel.add(addButton);
		functionPanel.add(removeButton);
		functionPanel.add(usageButton);
		functionPanel.add(statusButton);
		
		backButton = new JButton("Back");
		buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(backButton, BorderLayout.SOUTH);
		
		
		container.add(welcomeLabel, BorderLayout.NORTH);
		container.add(functionPanel, BorderLayout.CENTER);
		container.add(buttonPanel,BorderLayout.SOUTH);
		
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 370);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window	
		
		actionAdd();
		actionRemove();
		actionFindUser();
		actionCheckStation();
	}
	
	public void actionAdd() {
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddUser user = new AddUser();
				user.setVisible(true);
				user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionRemove() {
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveUser user = new RemoveUser();
				user.setVisible(true);
				user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionFindUser() {
		usageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FindUser user = new FindUser();
				user.setVisible(true);
				user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionCheckStation() {
		statusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckStation station = new CheckStation();
				station.setVisible(true);
				station.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	
/*	public static void main(String[] args) {
		AdminMenu adminMenu = new AdminMenu();
		adminMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
}
