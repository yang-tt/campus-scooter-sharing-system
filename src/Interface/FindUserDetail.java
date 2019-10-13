package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Control.Calculater;
import Control.Convert;
import Control.Admin_con;
import Tools.ReadData;
import Tools.WriteData;

/**
 * Admin check user usage details GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class FindUserDetail extends JFrame{
	
	private JLabel qmidLabel, nameLabel, emailLabel, telLabel, addressLabel, currentStatusLabel, fineLabel, moneyLabel;
	private JButton changeStatusButton, cancelButton;
	private String QMID, as[], s;
	private JLabel todayTotalLabel, totalLabel;
	private JPanel northPanel, centerPanel, sourthPanel, noticePanel;
	
	
	
	
	public FindUserDetail(String QMID) {
		
		super("User Detailed Information");
		
		this.QMID = QMID;
		
		Calculater calculater = new Calculater(QMID);
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
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
	    
		changeStatusButton = new JButton("Change Status");
		cancelButton = new JButton("Cancel");
			    
		qmidLabel = new JLabel("QM ID: " + as[0]);
		nameLabel = new JLabel("Name: " + as[1]);
		emailLabel = new JLabel("Email: " + as[2]);
		telLabel = new JLabel("Tel: " + as[3]);
		addressLabel = new JLabel("Address: " + as[4]);
		currentStatusLabel = new JLabel("Current Status: " + as[5]);
		fineLabel = new JLabel("Fine: " + as[6]);
		moneyLabel = new JLabel("Total Money: " + as[7]);
		
		todayTotalLabel = new JLabel("                    Today's Total Usage Time: " + calculater.calculate_today() + " s.", JLabel.LEFT);
		totalLabel = new JLabel("                    Total Usage Time: " + calculater.calculate_total() + " s.", JLabel.LEFT);

		
		noticePanel = new JPanel(new GridLayout(4,2,3,3));
		noticePanel.add(nameLabel);
		noticePanel.add(qmidLabel);
		noticePanel.add(emailLabel);
		noticePanel.add(telLabel);
		noticePanel.add(addressLabel);
		noticePanel.add(currentStatusLabel);
		noticePanel.add(fineLabel);
		noticePanel.add(moneyLabel);
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(noticePanel, BorderLayout.SOUTH);
				
		
		centerPanel = new JPanel(new GridLayout(6,1));
		centerPanel.add(todayTotalLabel);
		centerPanel.add(totalLabel);
			
		
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(cancelButton, BorderLayout.EAST);
		sourthPanel.add(changeStatusButton, BorderLayout.WEST);

		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(sourthPanel, BorderLayout.SOUTH);
		
		Convert convert = new Convert(QMID);
		
	    String[] Names = { "QMnumber", "Borrow Time", "Borrow Station", "Return Time", "Return Station"};
	    JTable table = new JTable(convert.convert_total(), Names);    
	    table.setPreferredScrollableViewportSize(new Dimension(450, 330)); 
	    JScrollPane scrollPane = new JScrollPane(table);
	    container.add(scrollPane, BorderLayout.CENTER);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 400);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actioncancel();
		actionchange();
	}
	
	public void actioncancel() {
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FindUser find = new FindUser();
				find.setVisible(true);
				find.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionchange() {
		changeStatusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_con admin_con = new Admin_con();
				admin_con.change(QMID);
				
				JOptionPane.showMessageDialog(null,"This user has been unlocked successfully!");
				FindUserDetail detail = new FindUserDetail(QMID);
				detail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
/*	public static void main(String[] args) {
		FindUserDetail detail = new FindUserDetail("11111");
		detail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
}
