package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Tools.ReadData;

/**
 * User return scooter GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class Return extends JFrame {
	
	private JLabel welcomeLabel;
	private JLabel nameLabel, qmidLabel, stationLabel;
	private JButton reselectButton, backButton;
	//private JLabel optionLabel, borrowLabel, returnLabel, usageLabel, fineLabel, logoutLabel;
	private JButton scooter[] = new JButton[8];
	private JLabel emptyLabel1,	emptyLabel2;
	private JPanel northPanel, centerPanel, sourthPanel;
	private JPanel welcomePanel, noticePanel, buttonPanel;
	private String QMID, Station, s, as[];
	

	public Return(String QMID, String Station){
		super("User_return");
		
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
		//JButton scooter[] = new JButton[8];
		scooter[0] = new JButton("Scooter1");
		scooter[1] = new JButton("Scooter2");
		scooter[2] = new JButton("Scooter3");
		scooter[3] = new JButton("Scooter4");
		scooter[4] = new JButton("Scooter5");
		scooter[5] = new JButton("Scooter6");
		scooter[6] = new JButton("Scooter7");
		scooter[7] = new JButton("Scooter8");
		
		List<String> dataList_station = new ArrayList<String>();
		if(Station.equals("A")) {
			dataList_station=ReadData.importCsv(new File("src/StationA.csv"));
		}
		else if(Station.equals("B")) {
			dataList_station=ReadData.importCsv(new File("src/StationB.csv"));	
		}
		else {
			dataList_station=ReadData.importCsv(new File("src/StationC.csv"));
		}
		
		if(dataList_station!=null && !dataList_station.isEmpty()){
				for(int i=0; i<dataList_station.size();i++ ){
					if(i!=0){
					String s=dataList_station.get(i);
					String[] as = s.split(",");
					if(as[1].equals("1")) {
						scooter[i-1].setForeground(Color.RED);
					}
					else {
						scooter[i-1].setForeground(Color.GREEN);
					}
				}
			}
		}
		
		centerPanel = new JPanel(new GridLayout(2,4));
		centerPanel.add(scooter[0]);
		centerPanel.add(scooter[1]);
		centerPanel.add(scooter[2]);
		centerPanel.add(scooter[3]);
		centerPanel.add(scooter[4]);
		centerPanel.add(scooter[5]);
		centerPanel.add(scooter[6]);
		centerPanel.add(scooter[7]);
			
		
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
		actionreselect();
		actionscooter1();
		actionscooter2();
		actionscooter3();
		actionscooter4();
		actionscooter5();
		actionscooter6();
		actionscooter7();
		actionscooter8();
	}
	
	public void actionback(){
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StudentMenu mainmenu = new StudentMenu(QMID, Station);
				mainmenu.setVisible(true);
				mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	public void actionscooter1(){
		scooter[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[0].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("1");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter2(){
		scooter[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[1].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("2");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter3(){
		scooter[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[2].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("3");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter4(){
		scooter[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[3].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("4");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter5(){
		scooter[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[4].getForeground() == Color.GREEN){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("5");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter6(){
		scooter[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[5].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("6");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter7(){
		scooter[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[6].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("7");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	public void actionscooter8(){
		scooter[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(scooter[7].getForeground().equals(Color.GREEN)){
					FlashReturn flash_Return = new FlashReturn(QMID, Station);
					flash_Return.setVisible(true);
					flash_Return.getID("8");
					flash_Return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please choose the GREEN one!");
				}
			}
		});
	}
	
	/*public static void main(String[] args) {
		Return return = new Return();
		return.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
}