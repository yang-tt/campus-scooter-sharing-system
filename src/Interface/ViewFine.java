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

import Control.Fine_con;
import Tools.ReadData;

/**
 * User checks fine condition GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class ViewFine extends JFrame {
	
	private JLabel welcomeLabel;
	private JLabel nameLabel, qmidLabel, stationLabel;
	private JLabel moneyLabel, fineLabel, stateLabel;
	private JButton reselectButton;
	private JButton backButton, rechargeButton, payButton;
	private JLabel emptyLabel1,	emptyLabel2;
	private JPanel northPanel, centerPanel, sourthPanel;
	private JPanel welcomePanel, noticePanel, buttonPanel;
	private String QMID, s, as[], Station, money, state, fine;
	
	public ViewFine(String QMID, String Station){
		super("Fine Information");
		
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
						state = as[5];
						fine = as[6];
						money = as[7];
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
		
		moneyLabel = new JLabel("                    The Money In Your Account: " + money, JLabel.LEFT);
		fineLabel = new JLabel("                    Total Fine Need To Pay: " + fine, JLabel.LEFT);
		stateLabel = new JLabel("                    Your Account Statue: " + state, JLabel.LEFT);

		backButton = new JButton("Back");
		rechargeButton = new JButton("Recharge");
		payButton = new JButton("Pay");
		
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
		centerPanel.add(stateLabel);
		centerPanel.add(moneyLabel);
		centerPanel.add(fineLabel);
			
		
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(backButton, BorderLayout.EAST);
		sourthPanel.add(payButton, BorderLayout.CENTER);
		sourthPanel.add(rechargeButton, BorderLayout.WEST);
		

		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(sourthPanel, BorderLayout.SOUTH);
		
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 430);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionback();
		actionrecharge();
		actionpay();
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
	
	public void actionrecharge(){
		rechargeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Recharge recharge = new Recharge(QMID, Station);
				recharge.setVisible(true);
				recharge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionpay(){
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Fine_con fine_con = new Fine_con(QMID);
				fine_con.pay();
				ViewFine view_fine = new ViewFine(QMID, Station);
				view_fine.setVisible(true);
				view_fine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}

}
