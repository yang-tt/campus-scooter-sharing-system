package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.Validation;

import Tools.ReadData;

/**
 * Main menu for the users GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class StudentMenu extends JFrame {
	
	private JLabel welcomeLabel;
	private JLabel nameLabel, qmidLabel, stationLabel;
	private JButton reselectButton;
	//private JLabel optionLabel, borrowLabel, returnLabel, usageLabel, fineLabel, logoutLabel;
	private JButton backButton, optionButton, borrowButton, returnButton, usageButton, fineButton, logoutButton;
	private JLabel emptyLabel1,	emptyLabel2;
	private JPanel northPanel, centerPanel, sourthPanel;
	private JPanel welcomePanel, noticePanel, buttonPanel;
	private String QMID, s, as[], Station;
	
	public StudentMenu(String QMID, String Station){
		super("User_menu");
		
		this.QMID = QMID;
		this.Station = Station;
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		
		welcomeLabel = new JLabel("Welcome to Scooter Sharing System", JLabel.CENTER);
		welcomePanel = new JPanel();
		welcomePanel.add(welcomeLabel);
		
		List<String> dataList_student=ReadData.importCsv(new File("src/Student.csv"));
		if(dataList_student!=null && !dataList_student.isEmpty()){
				for(int i=0; i<dataList_student.size();i++ ){
					if(i!=0){
					s=dataList_student.get(i);
					as = s.split(",");
					if(as[0].equals(QMID)) {
						break;
					}
				}
			}
		}
		
		nameLabel = new JLabel("                    Name: " + as[1], JLabel.LEFT);
		qmidLabel = new JLabel("                    QM ID: " + as[0], JLabel.LEFT);
		stationLabel = new JLabel("                    Station: " + Station, JLabel.LEFT);
		reselectButton = new JButton("Reselect");
		
		emptyLabel1 = new JLabel("                  ",JLabel.LEFT);
		emptyLabel2 = new JLabel("                  ",JLabel.LEFT);
		
		buttonPanel = new JPanel();
		buttonPanel.add(reselectButton);
		
		noticePanel = new JPanel(new GridLayout(3,2,3,3));
		noticePanel.add(nameLabel);
		noticePanel.add(emptyLabel1);
		noticePanel.add(qmidLabel);
		noticePanel.add(emptyLabel2);
		noticePanel.add(stationLabel);
		noticePanel.add(buttonPanel);
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(welcomePanel, BorderLayout.NORTH);
		northPanel.add(noticePanel, BorderLayout.SOUTH);
		
		
		/*
		optionLabel = new JLabel("Options", JLabel.CENTER);
		borrowLabel = new JLabel("1. Pick up a Scooter", JLabel.CENTER);
		returnLabel = new JLabel("2. Return a Scooter", JLabel.CENTER);
		usageLabel = new JLabel("3. View the Usage Condition", JLabel.CENTER);
		fineLabel = new JLabel("4. View the Fine Condition", JLabel.CENTER);
		logoutLabel = new JLabel("5. Log out", JLabel.CENTER);
		*/
		
		backButton = new JButton("Back");
		optionButton = new JButton("Options");
		borrowButton = new JButton("1. Pick up a Scooter");
		returnButton = new JButton("2. Return a Scooter");
		usageButton = new JButton("3. View the Usage Condition");
		fineButton = new JButton("4. View the Fine Condition");
		logoutButton = new JButton("5. Log out");
		
		
		centerPanel = new JPanel(new GridLayout(6,1));
		centerPanel.add(optionButton);
		centerPanel.add(borrowButton);
		centerPanel.add(returnButton);
		centerPanel.add(usageButton);
		centerPanel.add(fineButton);
		centerPanel.add(logoutButton);
			
		
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(backButton, BorderLayout.EAST);
		
		
		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(sourthPanel, BorderLayout.SOUTH);
		
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 430);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionback();
		actionborrow();
		actionreturn();
		actionreselect();
		actionusage();
		actionfine();
	}

	public void actionback(){
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				SelectStation selectstation = new SelectStation(QMID);
				selectstation.setVisible(true);
				selectstation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionreselect(){
		reselectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				SelectStation selectstation = new SelectStation(QMID);
				selectstation.setVisible(true);
				selectstation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionborrow(){
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Validation valid = new Validation(QMID);
				
				if(valid.borrow() == true && valid.status() == true) {
					Borrow borrow = new Borrow(QMID, Station);
					borrow.setVisible(true);
					borrow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else if(valid.borrow() == false && valid.status() == true) {
					JOptionPane.showMessageDialog(null,"You need to return the scooter first!");
				}
				else if(valid.status() == false) {
					JOptionPane.showMessageDialog(null,"You need to pay your bill first!");
				}
				
			}
		});
	}
	
	public void actionreturn(){
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Validation valid = new Validation(QMID);
				
				if(valid.back() == true && valid.status() == true) {
					Return back = new Return(QMID, Station);
					back.setVisible(true);
					back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else if(valid.back() == false && valid.status() == true) {
					JOptionPane.showMessageDialog(null,"You need to borrow the scooter first!");
				}
				else if(valid.status() == false) {
					JOptionPane.showMessageDialog(null,"You need to pay your bill first!");
				}
			}
		});
	}
	
	public void actionusage(){
		usageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewUsage usage = new ViewUsage(QMID, Station);
				usage.setVisible(true);
				usage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				
			}
		});
	}
	
	public void actionfine(){
		fineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewFine fine_stu = new ViewFine(QMID, Station);
				fine_stu.setVisible(true);
				fine_stu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				
			}
		});
	}
	
	public void getQMID(String QMID) {
		this.QMID = QMID;
	}
	
	public void getStation(String Station) {
		this.Station = Station;
	}
	
	/*public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
}