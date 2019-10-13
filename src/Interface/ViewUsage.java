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

import Control.Calculater;
import Tools.ReadData;

/**
 * User overview the usage condition GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class ViewUsage extends JFrame {
	
	private JLabel welcomeLabel;
	private JLabel nameLabel, qmidLabel, stationLabel;
	private JLabel thisTimeLabel, todayTotalLabel, totalLabel;
	private JButton reselectButton;
	//private JLabel optionLabel, borrowLabel, returnLabel, usageLabel, fineLabel, logoutLabel;
	private JButton backButton, reportButton, detailButton;
	private JLabel emptyLabel1,	emptyLabel2;
	private JPanel northPanel, centerPanel, sourthPanel;
	private JPanel welcomePanel, noticePanel, buttonPanel;
	private String QMID, s, as[], Station;
	
	public ViewUsage(String QMID, String Station){
		super("Usage Information");
		
		this.QMID = QMID;
		this.Station = Station;
		
		Calculater calculater = new Calculater(QMID);
		
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
		
		thisTimeLabel = new JLabel("                    Last Usage Time: " + calculater.calculate_now() + " s.", JLabel.LEFT);
		todayTotalLabel = new JLabel("                    Today's Total Usage Time: " + calculater.calculate_today() + " s.", JLabel.LEFT);
		totalLabel = new JLabel("                    Total Usage Time: " + calculater.calculate_total() + " s.", JLabel.LEFT);

		backButton = new JButton("Back");
		reportButton = new JButton("Weekly Report");
		detailButton = new JButton("More Details");
		
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
				
		
		centerPanel = new JPanel(new GridLayout(6,1));
		centerPanel.add(thisTimeLabel);
		centerPanel.add(todayTotalLabel);
		centerPanel.add(totalLabel);
			
		
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(backButton, BorderLayout.EAST);
		sourthPanel.add(reportButton, BorderLayout.CENTER);
		sourthPanel.add(detailButton, BorderLayout.WEST);
		

		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(sourthPanel, BorderLayout.SOUTH);
		
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 430);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionback();
		actiondetail();
		actionreport();
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
	
	public void actiondetail(){
		detailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				UsageDetail detail = new UsageDetail(QMID, Station);
				detail.setVisible(true);
				detail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionreport(){
		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				SendReport report = new SendReport(QMID, Station);
				report.setVisible(true);
				report.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}

}