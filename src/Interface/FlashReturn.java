package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import Control.Calculater;
import Control.BR_con;
import Tools.GetTime;
import Tools.ReadData;
import Tools.WriteData;

/**
 * User return scooter and flash and count down GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class FlashReturn extends JFrame {
	
	private JLabel welcomeLabel;
	private JLabel nameLabel, qmidLabel, stationLabel;
	private JButton reselectButton;
	//private JLabel optionLabel, borrowLabel, returnLabel, usageLabel, fineLabel, logoutLabel;
	private JButton backButton, flashButton, confirmButton;
	private JLabel emptyLabel1,	emptyLabel2, counterLabel;
	private JPanel northPanel, centerPanel, sourthPanel;
	private JPanel welcomePanel, noticePanel, buttonPanel;
	private JDialog dialog;
	private Timer timer;
	private Color c;
	private int time = 60;
	private String ID, QMID, Station, s1, s2, as1[], as2[];
	private MyThread thread;
	private boolean flag = true;
	
	public FlashReturn(String QMID, String Station){
		super("Comfirm");
		
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
					s1=dataList_student.get(i);
					as1 = s1.split(",");
					if(as1[0].equals(QMID)) {
						break;
					}
				}
			}
		}
		
		nameLabel = new JLabel("                    Name: " + as1[1], JLabel.LEFT);
		qmidLabel = new JLabel("                    QM ID: " + as1[0], JLabel.LEFT);
		stationLabel = new JLabel("                    Station: " + Station, JLabel.LEFT);
		reselectButton = new JButton("Reselect");
		
		emptyLabel1 = new JLabel("                  ",JLabel.LEFT);
		emptyLabel2 = new JLabel("                  ",JLabel.LEFT);
		counterLabel = new JLabel();
		
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
		
		
		backButton = new JButton("Back");
		flashButton = new JButton("Flash");
		confirmButton = new JButton("Confirm");
		
		centerPanel = new JPanel(new GridLayout(6,1));
		centerPanel.add(counterLabel);
		centerPanel.add(flashButton);
			
		
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(confirmButton, BorderLayout.EAST);
		sourthPanel.add(backButton, BorderLayout.WEST);
		
		
		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(sourthPanel, BorderLayout.SOUTH);
		
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 430);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionback();
		actionreselect();
		actionconfirm();
		
		
		ActionListener listener = new TimerListener();
		timer  = new Timer(1000,listener);
		timer.start();
		
		thread = new MyThread();
		thread.start();
		
	}
	
	public void getID(String ID) {
		this.ID = ID;
	}
	
	public Color ColorValue(){
		Random random = new Random();
		int r = random.nextInt(SetColor.values().length);
		
		SetColor d = null;
		for(SetColor c1 : SetColor.values()) {
			if(c1.ordinal() == r)
			{
				//System.out.println(c1.getMyColor());
				d = c1;
				break;
			}
		}
		return  d.getMyColor();
	}
	
	class TimerListener implements  ActionListener {
		 
		@Override
		public void actionPerformed(ActionEvent e) {
			c = ColorValue();	
			flashButton.setForeground(c);
		}
	}
	
	public void actionback(){
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Return back = new Return(QMID, Station);
				back.setVisible(true);
				back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	public void actionconfirm(){
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Calculater calculater = new Calculater(QMID);
				BR_con bR_con = new BR_con(ID, QMID, Station);
				bR_con.update_station("R");
				bR_con.add_return();
				
				int total_time = 0;
				total_time = calculater.calculate_today();
				flag = false;
				EndImp(bR_con.remove_borrow(), total_time);
			}
		});
	}
	
	class MyThread extends Thread{
		public void run() {
			while (time > 0){
				if(flag == false) {
					return;
				}
				else {
					time--;
					counterLabel.setText("Now you have " + time + "s to take your scooter!");
					try{
						Thread.sleep(1000);
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}

			}
			//Flash_Borrow.this.dispose();
			Borrow borrow = new Borrow(QMID, Station);
			borrow.setVisible(true);
			dispose();
		}
	};
	
	public void EndImp(int now_time, int today_time){
			int now = now_time;
			int today = today_time;
			BR_con bR_con = new BR_con(ID, QMID, Station);
			if(now > 1800) {
				bR_con.debt();
				
				JOptionPane.showMessageDialog(null,"Thank you for using scooter, this ride spent " + now + " s, have a nice day! Your using time has exceeded the free time, please pay for the bill first when you borrow a scooter next time!");
				
				Login log = new Login();
				log.setVisible(true);				
				log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
			else {
				if(today > 7200) {
					bR_con.debt();
					
					JOptionPane.showMessageDialog(null,"Thank you for using scooter, this ride spent " + now + " s, have a nice day! Your today using time is " + today + "s, which has exceeded the free time, please pay for the bill first when you borrow a scooter next time!");
					
					Login log = new Login();
					log.setVisible(true);
					log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Thank you for using scooter, this ride spent " + now + " s, have a nice day!");
					
					Login log = new Login();
					log.setVisible(true);	
					log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
			}
			
	}
	
	/*public static void main(String[] args) {
		Flash_Return flash = new Flash_Return();
		flash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
}
