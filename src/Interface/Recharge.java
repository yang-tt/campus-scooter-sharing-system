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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Control.Fine_con;
import Tools.ReadData;
import Tools.WriteData;

import javax.swing.JTable;

/**
 * User recharges money GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class Recharge extends JFrame {
		
		private JLabel welcomeLabel;
		private JLabel nameLabel, qmidLabel, stationLabel;
		private JLabel chooseLabel;
		private JButton reselectButton;
		//private JLabel optionLabel, borrowLabel, returnLabel, usageLabel, fineLabel, logoutLabel;
		private JButton backButton, confirmButton;
		private JLabel emptyLabel1,	emptyLabel2;
		private JPanel northPanel, centerPanel, sourthPanel;
		private JPanel welcomePanel, noticePanel, buttonPanel;
		private JTextField charge;
		private String QMID, s, as[], Station;
		
		public Recharge(String QMID, String Station){
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
			
			charge = new JTextField(5);
			
			nameLabel = new JLabel("                    Name: " + as[1], JLabel.LEFT);
			qmidLabel = new JLabel("                    QM ID: " + as[0], JLabel.LEFT);
			stationLabel = new JLabel("                    Station: " + Station, JLabel.LEFT);
			reselectButton = new JButton("Reselect");
			
			emptyLabel1 = new JLabel("                  ",JLabel.LEFT);
			emptyLabel2 = new JLabel("                  ",JLabel.LEFT);
			
			chooseLabel = new JLabel("                    Please Input The Amount Of Money You Want To Recharge: ", JLabel.LEFT);

			backButton = new JButton("Back");
			confirmButton = new JButton("Confirm");
			
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
					
			
			centerPanel = new JPanel(new FlowLayout());
			centerPanel.add(chooseLabel);
			centerPanel.add(charge);
			
			
			sourthPanel = new JPanel(new BorderLayout());
			sourthPanel.add(backButton, BorderLayout.EAST);
			sourthPanel.add(confirmButton, BorderLayout.WEST);
			

			container.add(northPanel, BorderLayout.NORTH);
			container.add(centerPanel, BorderLayout.CENTER);
			container.add(sourthPanel, BorderLayout.SOUTH);
		    
			
			setResizable(false);	//set frame can not be resize
			setSize(500, 430);	//set frame's size
			setVisible(true);	//display frame
			setLocationRelativeTo(null);	//display in the center of window
			
			actionback();
			actionconfirm();
		}

		public void actionback(){
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					ViewFine fine_stu = new ViewFine(QMID, Station);
					fine_stu.setVisible(true);
					fine_stu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
			});
		}
		
		public void actionconfirm(){
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String pay = charge.getText();
					JOptionPane.showMessageDialog(null,"You have recharged successfully!");
					
					Fine_con fine_con = new Fine_con(QMID);
					fine_con.recharge(pay);
					
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

	}

