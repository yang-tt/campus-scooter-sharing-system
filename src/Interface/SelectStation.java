package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * User choose a station to operate GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class SelectStation extends JFrame{
	
	private JLabel selectLabel;
	private JRadioButton aButton, bButton, cButton;
	private ButtonGroup stationGroup;
	private JButton confirmButton, backButton;
	private JLabel emptyLabel1, emptyLabel2;
	private JPanel confirmPanel, backPanel, buttonPanel, centerPanel;
	private String QMID, Station;
	
	public SelectStation(String QMID) {
		super("select_station");
		
		this.QMID = QMID;
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		selectLabel = new JLabel("Please select the docking station:", JLabel.CENTER);
		
		aButton = new JRadioButton("Station A (Library)", false);
		bButton = new JRadioButton("Station B (Informatics Teaching Laboratories)", false);
		cButton = new JRadioButton("Station C (Village Shop)", false);
		
		buttonPanel = new JPanel(new GridLayout(3,1));
		buttonPanel.add(aButton);
		buttonPanel.add(bButton);
		buttonPanel.add(cButton);
		
		emptyLabel1 = new JLabel("                       ");
		emptyLabel2 = new JLabel("     ");
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(buttonPanel, BorderLayout.CENTER);
		centerPanel.add(emptyLabel1, BorderLayout.WEST);
		centerPanel.add(emptyLabel2, BorderLayout.EAST);
		
		stationGroup = new ButtonGroup();
		stationGroup.add(aButton);
		stationGroup.add(bButton);
		stationGroup.add(cButton);
		
		confirmButton = new JButton("Confirm");
		confirmPanel = new JPanel();
		confirmPanel.add(confirmButton);
		
		
		backButton = new JButton("Back");
		backPanel = new JPanel();
		backPanel.add(backButton);
		
		JPanel confirm_back = new JPanel(new GridLayout(1,2));
		confirm_back.add(confirmPanel);
		confirm_back.add(backPanel);
		

		container.add(selectLabel);
		container.add(centerPanel);
		container.add(confirm_back);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionconfirm();
		actionback();
		actionstationA();
		actionstationB();
		actionstationC();
	}
	
	public void getQMID(String QMID) {
		this.QMID= QMID;
	}
	
	public void actionconfirm(){
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StudentMenu mainmenu = new StudentMenu(QMID, Station);
				mainmenu.setVisible(true);
				mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				dispose();
			}
		});
	}
	public void actionback(){
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Login log = new Login();
				log.setVisible(true);
				log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				dispose();
			}
		});
	}
	
	public void actionstationA(){
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Station = "A";
			}
		});
	}
	public void actionstationB(){
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Station = "B";
			}
		});
	}
	public void actionstationC(){
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Station = "C";
			}
		});
	}
	

	/*public static void main(String[] args) {
		SelectStation station = new SelectStation();
		station.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
}
