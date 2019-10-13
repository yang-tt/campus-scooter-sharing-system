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

import Control.Calculater;
import Control.Convert;
import Tools.GetTime;
import Tools.ReadData;

import javax.swing.JTable;

/**
 * User get weekly report GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class SendReport extends JFrame {
		
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
		private int total_day, week_inter, week_begin, week_end, month_begin, day_begin, month_end, day_end;
		
		public SendReport(String QMID, String Station){
			super("User_menu");
			
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
			
			//thisTimeLabel = new JLabel("                    Name: " + as[1], JLabel.LEFT);
			todayTotalLabel = new JLabel("                    Today's Total Usage Time: " + calculater.calculate_today() + " s.", JLabel.LEFT);
			totalLabel = new JLabel("                    Total Usage Time: " + calculater.calculate_total() + " s.", JLabel.LEFT);

			backButton = new JButton("Back");
//			reportButton = new JButton("Weekly Report");
//			detailButton = new JButton("More Details");
			
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
			centerPanel.add(todayTotalLabel);
			centerPanel.add(totalLabel);
				
			
			sourthPanel = new JPanel(new BorderLayout());
			sourthPanel.add(backButton, BorderLayout.EAST);
//			sourthPanel.add(reportButton, BorderLayout.CENTER);
//			sourthPanel.add(detailButton, BorderLayout.WEST);
			

			container.add(northPanel, BorderLayout.NORTH);
			container.add(centerPanel, BorderLayout.CENTER);
			container.add(sourthPanel, BorderLayout.SOUTH);
			
			GetTime gettime = new GetTime();
			total_day = calculater.calculate_day(gettime.getMonth(), gettime.getDay());
			
			week_inter = total_day/7;
			week_begin = week_inter * 7;
			week_end = week_begin + 6;
			month_begin = calculater.calculate_month(week_begin);
			month_end = calculater.calculate_month(week_end);
			day_begin = calculater.calculate_date(month_begin, week_begin);
			day_end = calculater.calculate_date(month_end, week_end);

			
			Convert convert = new Convert(QMID);
			
		    String[] Names = { "QMnumber", "Borrow Time", "Borrow Station", "Return Time", "Return Station"};
		    JTable table = new JTable(convert.convert_week(month_begin, day_begin, month_end, day_end), Names);    
		    table.setPreferredScrollableViewportSize(new Dimension(450, 330)); 
		    JScrollPane scrollPane = new JScrollPane(table);
		    container.add(scrollPane, BorderLayout.CENTER);
		    
			
			
			setResizable(false);	//set frame can not be resize
			setSize(500, 430);	//set frame's size
			setVisible(true);	//display frame
			setLocationRelativeTo(null);	//display in the center of window
			
			actionback();
		}

		public void actionback(){
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					ViewUsage usage_stu = new ViewUsage(QMID, Station);
					usage_stu.setVisible(true);
					usage_stu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
			});
		}

	}


