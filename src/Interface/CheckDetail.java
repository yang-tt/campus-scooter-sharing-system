package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Tools.ReadData;

/**
 * Admin check station detail GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class CheckDetail extends JFrame{
	private JLabel welcomeLabel;
	private JLabel totalLabel, inUsingLabel, stationLabel;
	private JButton reselectButton, backButton;
	//private JLabel optionLabel, borrowLabel, returnLabel, usageLabel, fineLabel, logoutLabel;
	private JButton scooter[] = new JButton[8];
	private JLabel emptyLabel1,	emptyLabel2;
	private JPanel northPanel, centerPanel, sourthPanel;
	private JPanel welcomePanel, noticePanel, buttonPanel;
	private String Station;
	

	public CheckDetail(String Station) {
		super("Station Information");
		
		this.Station = Station;
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		
		welcomeLabel = new JLabel("Welcome to Scooter Sharing System", JLabel.CENTER);
		welcomePanel = new JPanel();
		welcomePanel.add(welcomeLabel);
		
		stationLabel = new JLabel("                    Station: " + Station, JLabel.LEFT);
		totalLabel = new JLabel("                    Total Slot:   8", JLabel.LEFT);
		//inUsingLabel = new JLabel("                    In Using Scooter: ", JLabel.LEFT);
		reselectButton = new JButton("Reselect");
		
		emptyLabel1 = new JLabel("                  ",JLabel.LEFT);
		emptyLabel2 = new JLabel("                  ",JLabel.LEFT);
		
		buttonPanel = new JPanel();
		buttonPanel.add(reselectButton);
		
		noticePanel = new JPanel(new GridLayout(2,2,3,3));
		noticePanel.add(stationLabel);
		noticePanel.add(buttonPanel);
		noticePanel.add(totalLabel);
		noticePanel.add(emptyLabel1);
		//noticePanel.add(inUsingLabel);
		//noticePanel.add(emptyLabel2);
		
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(welcomePanel, BorderLayout.NORTH);
		northPanel.add(noticePanel, BorderLayout.SOUTH);
		
		
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
						scooter[i-1].setForeground(Color.GREEN);
					}
					else {
						scooter[i-1].setForeground(Color.RED);
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
	}
	
	public void actionback(){
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AdminMenu mainmenu = new AdminMenu();
				mainmenu.setVisible(true);
				mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionreselect(){
		reselectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				CheckStation selectstation = new CheckStation();
				selectstation.setVisible(true);
				selectstation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	

}
